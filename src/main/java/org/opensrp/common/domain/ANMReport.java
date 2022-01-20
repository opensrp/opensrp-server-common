package org.opensrp.common.domain;

import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ANMReport {
	
	@JsonProperty
	private List<ANMIndicatorSummary> summaries;
	
	@JsonProperty
	private String anmIdentifier;
	
	public ANMReport() {
	}
	
	public ANMReport(String anmIdentifier, List<ANMIndicatorSummary> summaries) {
		this.anmIdentifier = anmIdentifier;
		this.summaries = summaries;
	}
	
	public List<ANMIndicatorSummary> summaries() {
		return summaries;
	}
	
	public String anmIdentifier() {
		return anmIdentifier;
	}
	
	@Override
	public final boolean equals(Object o) {
		return EqualsBuilder.reflectionEquals(this, o);
	}
	
	@Override
	public final int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
