package org.raman.springframwork.stepscope;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.annotation.BeforeRead;
import org.springframework.batch.item.ItemReader;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

@Component
@Scope("step")
public class DoNothingItemReader  implements ItemReader<String>{
	private int readCount=0;
	private static Log LOGGER = LogFactory.getLog(DoNothingItemReader.class);
	
	public DoNothingItemReader() {
		LOGGER.info(String.format("New %s created" ,ClassUtils.getShortName(this.getClass())));
	}

	@BeforeRead
	void beforeRead(){
		readCount++;
	}

		@Override
	public String read() throws Exception{
		LOGGER.info("Nothing to read...");
		return readCount<3? "test":null;
	}

}
