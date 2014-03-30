package org.raman.springframwork.configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
public class DatasourceConfig {

	@Autowired
	private Environment environment;

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	private DataSource jobDataSource;
	/*
	  <jdbc:initialize-database data-source="dataSource">
	   		<jdbc:script  location="classpath:batch/classic/schema.sql" />
	  </jdbc:initialize-database>
	 */
	@PostConstruct
	protected void initialize() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(resourceLoader.getResource("classpath:batch/classic/schema.sql"));
		populator.setContinueOnError(true);
		DatabasePopulatorUtils.execute(populator, jobDataSource);
	}

	/**
	 * TODO
	 * The batch-classic.xml use two datasource, but we use only one datasource because we 
	 * get IllegalStateException To use the default BatchConfigurer the context must contain precisely one DataSource, found 2
	 * 
	 * We will need to write our own BatchConfigurer in order to use two datasources
	 *  
	 */
	/*
	 * <bean id="dataSource"  class="org.springframework.jdbc.datasource.SingleConnectionDataSource">
		  	<property name="driverClassName" value="org.h2.Driver" />
		  	<property name="url" value="jdbc:h2:mem:classic" /> 
		  	<property name="username" value="sa" />
		  	<property name="password" value="" />
		    <property name="suppressClose" value="true" />
	   </bean>
	 */
//	@Bean(destroyMethod = "destroy")
//	public DataSource classicDataSource() {
//		SingleConnectionDataSource dataSource = new SingleConnectionDataSource();
//		dataSource.setDriverClassName("org.h2.Driver");
//		dataSource.setUrl("jdbc:h2:mem:classic");
//		dataSource.setUsername("sa");
//		dataSource.setPassword("");
//		dataSource.setSuppressClose(true);
//		return dataSource;
//	}

}