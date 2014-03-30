package org.raman.springframwork.classic;

import java.io.File;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class DeleteFileTaskelt implements Tasklet {

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		final StepContext stepContext = chunkContext.getStepContext();
		final JobExecution jobExecution = stepContext.getStepExecution().getJobExecution();
		final ExecutionContext jobExecutionContext = jobExecution.getExecutionContext();
		final File tempCsvFile = (File) jobExecutionContext.get("TEMP_FILE");
		if(!tempCsvFile.exists() || !tempCsvFile.delete()){
			contribution.setExitStatus(ExitStatus.FAILED);
		}
		return RepeatStatus.FINISHED;
	}
	
}
