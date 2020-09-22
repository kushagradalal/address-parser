package com.kushagra.addressparser.model;

import java.util.Set;

public class StateCities {

	private Set<String> cities;
	private String state;
	private String stateCode;

	public Set<String> getCities() {
		return cities;
	}

	public void setCities(Set<String> cities) {
		this.cities = cities;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

}
