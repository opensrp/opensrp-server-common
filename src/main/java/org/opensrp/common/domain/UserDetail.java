package org.opensrp.common.domain;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDetail implements Serializable {
	
	@JsonProperty
	private String userName;
	
	@JsonProperty
	private List<String> roles;
	
	private String preferredName;
	
	public UserDetail(String userName, List<String> roles) {
		this.userName = userName;
		this.roles = roles;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public List<String> getRoles() {
		return roles;
	}

	public String getPreferredName() {
		return preferredName;
	}

	public void setPreferredName(String preferredName) {
		this.preferredName = preferredName;
	}
	
}
