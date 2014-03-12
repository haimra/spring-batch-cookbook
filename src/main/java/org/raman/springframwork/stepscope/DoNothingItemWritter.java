package org.raman.springframwork.stepscope;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

@Component
@Scope("step")
public class DoNothingItemWritter implements ItemWriter<String[]> {
	private static Log LOGGER = LogFactory.getLog(DoNothingItemWritter.class);

	public DoNothingItemWritter() {
		LOGGER.info(String.format("New %s created" ,ClassUtils.getShortName(this.getClass())));
	}

	@Override
	public void write(List<? extends String[]> items) throws Exception {
		LOGGER.info("Nothing to write...");
		
	}

}
