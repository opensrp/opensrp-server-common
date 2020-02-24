package org.opensrp.common.monitor;

import java.util.logging.Logger;

import org.opensrp.common.util.DateUtil;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Monitor {
	
	public static final String LOGGER_NAME = "DRISHTI_MONITOR";
	
	private static Logger logger = LoggerFactory.getLogger(LOGGER_NAME);
	
	public Probe start(Metric metric) {
		return new Probe(metric);
	}
	
	public void end(Probe probe) {
		addObservationFor(probe.metric(), probe.value());
	}
	
	public void addObservationFor(Metric metric, long value) {
		logger.info(metric.name() + " " + value + " " + DateUtil.now().getMillis());
	}
}
