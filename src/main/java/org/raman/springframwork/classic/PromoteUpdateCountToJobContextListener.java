package org.raman.springframwork.classic;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepListener;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.stereotype.Component;

@Component
public class PromoteUpdateCountToJobContextListener implements StepListener {

	@AfterStep
	public ExitStatus afterStep(StepExecution stepExecution){
		int writeCount = stepExecution.getWriteCount();
		stepExecution.getJobExecution().getExecutionContext().put(stepExecution.getStepName()+".WriteCount", writeCount);
		return stepExecution.getExitStatus();
	}
	
	
}
