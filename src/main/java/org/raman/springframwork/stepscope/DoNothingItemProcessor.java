package org.raman.springframwork.stepscope;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;


@Component
@Scope("step")
public class DoNothingItemProcessor implements ItemProcessor<String, String> {
	private static Log LOGGER = LogFactory.getLog(DoNothingItemProcessor.class);
	
	
	public DoNothingItemProcessor() {
		LOGGER.info(String.format("New %s created" ,ClassUtils.getShortName(this.getClass())));
	}


	@Override
	public String process(String i) throws Exception {
		LOGGER.info("Nothing to process...");
		return i;
	}

}
