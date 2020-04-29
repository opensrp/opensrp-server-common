package org.opensrp.common.domain;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDetail implements Serializable {
	
	private static final long serialVersionUID = -3954547752263574520L;

	@JsonProperty("username")
	private String userName;
	
	@JsonProperty("user_id")
	private String identifier;
	
	@JsonProperty
	private List<String> roles;
	
	@JsonProperty("preferred_name")
	private String preferredName;
	
	@JsonProperty("family_name")
	private String familyName;
	
	@JsonProperty("given_name")
	private String givenName;
	
	private String email;
	
	@JsonProperty("email_verified")
	private boolean emailVerified;
	
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
	
	public String getIdentifier() {
		return identifier;
	}
	
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	public String getFamilyName() {
		return familyName;
	}
	
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	
	public String getGivenName() {
		return givenName;
	}
	
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean isEmailVerified() {
		return emailVerified;
	}
	
	public void setEmailVerified(boolean emailVerified) {
		this.emailVerified = emailVerified;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
}
