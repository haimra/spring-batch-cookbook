package org.raman.springframwork.configuration;

import java.net.MalformedURLException;

import javax.sql.DataSource;

import org.raman.springframwork.classic.CustomerFieldSetMapper;
import org.raman.springframwork.classic.domain.Customer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * batch configuration using spring java configuration
 * http://docs.spring.io/spring-batch/reference/html/configureJob.html
 * https://blog.codecentric.de/en/2013/06/spring-batch-2-2-javaconfig-part-1-a-comparison-to-xml/
 */
@Configuration
@ComponentScan(basePackages = { "org.raman.springframwork.classic" })
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	private JobBuilderFactory jobs;

	@Autowired
	private StepBuilderFactory steps;

	@Autowired
	private JobRegistry jobRegistry;
	
	@Autowired
	private DataSource jobDataSource;

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	private CustomerFieldSetMapper customerFieldSetMapper;

	@Autowired
	private ItemProcessor<Customer, Customer> customerItemProcessor;

	@Autowired
	protected ItemPreparedStatementSetter<Customer> customerPreparedStatementSetter;

	@Bean
	public Job classicScenario() throws Exception {
		return jobs.get("classicScenario").start(processCsv()).build();
	}

	@Bean
	protected Step processCsv() throws MalformedURLException {
		return steps.get("processCsv").transactionManager(classicTransactionManager()).<Customer, Customer> chunk(10)
				.reader(reader()).processor(customerItemProcessor).writer(writer()).build();
	}

	@Bean
	protected ItemReader<Customer> reader() throws MalformedURLException {
		FlatFileItemReader<Customer> reader = new FlatFileItemReader<Customer>();
		reader.setResource(resourceLoader.getResource("file:/temp/customers.csv"));
		reader.setLineMapper(defaultLineMapper());
		return reader;
	}

	@Bean
	protected ItemWriter<Customer> writer() {
		JdbcBatchItemWriter<Customer> jdbcWriter = new JdbcBatchItemWriter<>();
		jdbcWriter.setSql("INSERT INTO customer (id,first_name,last_name,card_number) VALUES(?,?,?,?)");
		jdbcWriter.setItemPreparedStatementSetter(customerPreparedStatementSetter);
		jdbcWriter.setDataSource(jobDataSource);
		return jdbcWriter;
	}

	@Bean
	protected DefaultLineMapper<Customer> defaultLineMapper() {
		DefaultLineMapper<Customer> defaultLineMapper = new DefaultLineMapper<>();
		defaultLineMapper.setFieldSetMapper(customerFieldSetMapper);
		defaultLineMapper.setLineTokenizer(customerLineTokenizer());
		return defaultLineMapper;

	}

	@Bean
	protected DelimitedLineTokenizer customerLineTokenizer() {
		DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer(",");
		delimitedLineTokenizer.setNames(new String[] { "id", "firstName", "lastName", "cardNumber" });
		return delimitedLineTokenizer;
	}

	@Bean
	protected PlatformTransactionManager classicTransactionManager(){
		return new DataSourceTransactionManager(jobDataSource);
	}
	
	@Bean
	protected JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor() throws Exception {
		JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor = new JobRegistryBeanPostProcessor();
		jobRegistryBeanPostProcessor.setJobRegistry(jobRegistry);
		jobRegistryBeanPostProcessor.afterPropertiesSet();
		return jobRegistryBeanPostProcessor;
	}
	
}