package org.opensrp.common.monitor;

import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.internal.WhiteboxImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Probe.class})
public class MonitorTest {

    @Before
    public void setUp() {
        PowerMockito.mockStatic(System.class);
    }

    @Test
    public void probeTest() {
        BDDMockito.given(System.nanoTime()).willReturn(100l, 150l);
        Monitor monitor = new Monitor();
        Probe probe = monitor.start(Metric.REPORTING_ANM_REPORTS_CACHE_TIME);
        long value = probe.value();
        assertEquals(50, value);
    }

    @Test
    public void monitorLoggingTest() {
        Monitor monitor = PowerMockito.spy(new Monitor());
        Logger mockLogger = PowerMockito.mock(Logger.class);
        WhiteboxImpl.setInternalState(monitor, "logger", mockLogger);
        BDDMockito.given(System.nanoTime()).willReturn(100l, 150l);
        Probe probe = monitor.start(Metric.REPORTING_ANM_REPORTS_CACHE_TIME);
        monitor.end(probe);
        verify(monitor).addObservationFor(eq(Metric.REPORTING_ANM_REPORTS_CACHE_TIME), eq(50L));
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockLogger).info(stringArgumentCaptor.capture());

        String result = stringArgumentCaptor.getValue();
        assertTrue(result.contains("50"));
        assertTrue(result.contains(Metric.REPORTING_ANM_REPORTS_CACHE_TIME.name()));
    }
}
