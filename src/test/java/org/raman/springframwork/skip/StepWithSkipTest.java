package org.raman.springframwork.skip;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInterruptedException;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration( locations={"classpath:META-INF/spring/batch-with-skip.xml","classpath:META-INF/spring/test-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class StepWithSkipTest {

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;
	
	@Test
	public void testStepWithSkip() throws JobInterruptedException{
		JobExecution execution = jobLauncherTestUtils.launchStep("stepWithSkip");
		
	}
}
