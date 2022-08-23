package org.opensrp.common.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang.StringUtils.isBlank;
import static org.opensrp.common.AllConstants.ReportDataParameters.*;

public class ReportingData implements Serializable {

    private static final long serialVersionUID = 454645765753L;

    @JsonProperty
    private String type;

    @JsonProperty
    private Map<String, String> data;

    private ReportingData() {
    }

    public ReportingData(String type) {
        this.type = type;
        data = new HashMap<>();
    }

    public ReportingData(String type, Map<String, String> data) {
        this.type = type;
        this.data = data;
    }

    public static ReportingData serviceProvidedData(String anmIdentifier, String externalId, Indicator indicator,
                                                    String date, Location location, String dristhiEntityId) {
        return new ReportingData(SERVICE_PROVIDED_DATA_TYPE).with(ANM_IDENTIFIER, anmIdentifier)
                .with(INDICATOR, indicator.value()).with(EXTERNAL_ID, externalId).with(VILLAGE, location.village())
                .with(SUB_CENTER, location.subCenter()).with(PHC, location.phc()).with(SERVICE_PROVIDED_DATE, date)
                .with(DRISTHI_ENTITY_ID, dristhiEntityId).with(SERVICE_PROVIDER_TYPE, SERVICE_PROVIDER_ANM);
    }

    public static ReportingData anmReportData(String anmIdentifier, String externalId, Indicator indicator, String date) {
        return new ReportingData(ANM_REPORT_DATA_TYPE).with(ANM_IDENTIFIER, anmIdentifier).with(INDICATOR, indicator.value())
                .with(EXTERNAL_ID, externalId).with(SERVICE_PROVIDED_DATE, date);
    }

    public ReportingData with(String key, String value) {
        data.put(key, value);
        return this;
    }

    public ReportingData withQuantity(String quantity) {
        data.put(QUANTITY, quantity);
        return this;
    }

    public String get(String key) {
        return data.get(key);
    }

    public String type() {
        return type;
    }

    public ArrayList getMissingReportDataForANMReport() {
        ArrayList missingData = new ArrayList();
        addToMissingDataIfFieldValueIsMissing(ANM_IDENTIFIER, missingData);
        addToMissingDataIfFieldValueIsMissing(EXTERNAL_ID, missingData);
        addToMissingDataIfFieldValueIsMissing(INDICATOR, missingData);
        addToMissingDataIfFieldValueIsMissing(SERVICE_PROVIDED_DATE, missingData);
        return missingData;
    }

    public ArrayList getMissingReportDataForServiceProvided() {
        ArrayList missingData = new ArrayList();
        addToMissingDataIfFieldValueIsMissing(ANM_IDENTIFIER, missingData);
        addToMissingDataIfFieldValueIsMissing(SERVICE_PROVIDER_TYPE, missingData);
        addToMissingDataIfFieldValueIsMissing(EXTERNAL_ID, missingData);
        addToMissingDataIfFieldValueIsMissing(INDICATOR, missingData);
        addToMissingDataIfFieldValueIsMissing(SERVICE_PROVIDED_DATE, missingData);
        addToMissingDataIfFieldValueIsMissing(VILLAGE, missingData);
        addToMissingDataIfFieldValueIsMissing(SUB_CENTER, missingData);
        addToMissingDataIfFieldValueIsMissing(PHC, missingData);
        addToMissingDataIfFieldValueIsMissing(DRISTHI_ENTITY_ID, missingData);
        return missingData;
    }

    private void addToMissingDataIfFieldValueIsMissing(String fieldName, ArrayList missingData) {
        if (isBlank(get(fieldName))) {
            missingData.add(fieldName);
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public final boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public final int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
