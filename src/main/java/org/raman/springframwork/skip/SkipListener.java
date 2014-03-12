package org.raman.springframwork.skip;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.annotation.OnSkipInProcess;
import org.springframework.batch.core.annotation.OnSkipInRead;
import org.springframework.batch.core.annotation.OnSkipInWrite;
import org.springframework.stereotype.Component;

@Component
public class SkipListener {
	private static Log LOGGER = LogFactory.getLog(SkipListener.class);

	@OnSkipInRead
	public void log(final Throwable t) throws IOException {
		LOGGER.info(String.format("Skip in read due to %s", t.getMessage()));
	}

	@OnSkipInWrite
	public void log(final Object objectToWrite, final Throwable t)
			throws IOException {
		LOGGER.info(String.format("Skip in write due to %s", t.getMessage()));
	}

	@OnSkipInProcess
	public void onSkipInProcess(final Object objectToWrite, final Throwable t)
			throws IOException {
		LOGGER.info(String.format("Skip %s in process due to %s",objectToWrite, t.getMessage()));
	}
}
