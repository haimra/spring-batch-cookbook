package org.raman.springframwork.classic;

import java.io.File;

import org.raman.springframwork.classic.strategy.Strategy;
import org.raman.springframwork.classic.strategy.StrategyLocator;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;

public class DeleteFileTaskelt implements Tasklet {
	@Value("#{jobParameters['strategy']}")
	private String strategyEnumStr;
	
	private StrategyLocator strategyLocator;
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		Strategy strategy = strategyLocator.lookup(strategyEnumStr);
		ExecutionContext executionContext = chunkContext.getStepContext().getStepExecution().getExecutionContext();
		//int executeStrategyResult = context.executeStrategy(executionContext.getInt("a"), executionContext.getInt("c"));
		int executeStrategyResult = strategy.execute(1, 2);
		final StepContext stepContext = chunkContext.getStepContext();
		final JobExecution jobExecution = stepContext.getStepExecution().getJobExecution();
		final ExecutionContext jobExecutionContext = jobExecution.getExecutionContext();
		final File tempCsvFile = (File) jobExecutionContext.get("TEMP_FILE");
		if(!tempCsvFile.exists() || !tempCsvFile.delete()){
			contribution.setExitStatus(ExitStatus.FAILED);
		}
		return RepeatStatus.FINISHED;
	}

	public void setStrategyLocator(StrategyLocator strategyLocator) {
		this.strategyLocator = strategyLocator;
	}
	
}
