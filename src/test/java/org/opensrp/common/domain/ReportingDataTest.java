package org.opensrp.common.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import nl.jqno.equalsverifier.Warning;
import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

/**
 * Created by real on 12/07/17.
 */
public class ReportingDataTest {
	
	@Test
	public void testConstructorNGettersOfReportingData() {
		Map<String, String> data = new HashMap<>();
		data.put("quantity", "open");
		ReportingData reportingData1, reportingData2, reportingData3, reportingData4, reportingData5;
		reportingData1 = new ReportingData("new data");
		
		assertEquals("new data", reportingData1.type());
		assertNotSame("no data", reportingData1.type());
		
		reportingData3 = ReportingData.anmReportData("anmIdentifier", "", Indicator.ANC, "");
		ArrayList missingList = reportingData3.getMissingReportDataForANMReport();
		ArrayList missingListExpected = new ArrayList();
		missingListExpected.add("externalId");
		missingListExpected.add("date");
		assertTrue(missingList.equals(missingListExpected));
		missingListExpected.add("anmIdentifier");
		assertFalse(missingList.equals(missingListExpected));
		
		Location location = new Location("Nandanpur", "gangapur", "house");
		reportingData4 = ReportingData.serviceProvidedData("anmIdentifier", "externalId", Indicator.ANC4, "date", location,
		    "");
		ArrayList missingReportDataForServiceProvidedList = reportingData4.getMissingReportDataForServiceProvided();
		ArrayList expectedMissingReportDataForServiceProvidedList = new ArrayList();
		expectedMissingReportDataForServiceProvidedList.add("dristhiEntityId");
		assertTrue(missingReportDataForServiceProvidedList.equals(expectedMissingReportDataForServiceProvidedList));
		expectedMissingReportDataForServiceProvidedList.add("anmIdentifier");
		assertFalse(missingReportDataForServiceProvidedList.equals(expectedMissingReportDataForServiceProvidedList));
		
		reportingData2 = new ReportingData("anc report", data);
		assertTrue(reportingData2.toString().contains("type=anc"));
		assertFalse(reportingData2.toString().contains("type-anc4"));
		
		reportingData2.withQuantity("better");
		System.out.println(reportingData2.toString());
		assertTrue(reportingData2.toString().contains("quantity=better"));
		assertFalse(reportingData2.toString().contains("quantity=close"));
	}
	
	@Test
	public void equalsContract() {
		EqualsVerifier.forClass(ReportingData.class)
				.suppress(Warning.NONFINAL_FIELDS)
				.verify();
	}
}
