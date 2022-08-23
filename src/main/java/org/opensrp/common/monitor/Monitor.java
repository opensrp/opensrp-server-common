package org.opensrp.common.monitor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

@Component
public class Monitor {

    public static final String LOGGER_NAME = "DRISHTI_MONITOR";

    private final Logger logger = LogManager.getLogger(LOGGER_NAME);

    public Probe start(Metric metric) {
        return new Probe(metric);
    }

    public void end(Probe probe) {
        addObservationFor(probe.metric(), probe.value());
    }

    public void addObservationFor(Metric metric, long value) {
        logger.info(metric.name() + " " + value + " " + DateTime.now());
    }
}
