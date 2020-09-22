package com.kushagra.addressparser.model;

public class InputCSV {
	private String city;
	private String stateShort;
	private String stateFull;
	private String county;
	private String cityAlias;

	public InputCSV() {
	}

	public InputCSV(String city, String stateShort, String stateFull, String county, String cityAlias) {
		super();
		this.city = city;
		this.stateShort = stateShort;
		this.stateFull = stateFull;
		this.county = county;
		this.cityAlias = cityAlias;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStateShort() {
		return stateShort;
	}

	public void setStateShort(String stateShort) {
		this.stateShort = stateShort;
	}

	public String getStateFull() {
		return stateFull;
	}

	public void setStateFull(String stateFull) {
		this.stateFull = stateFull;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getCityAlias() {
		return cityAlias;
	}

	public void setCityAlias(String cityAlias) {
		this.cityAlias = cityAlias;
	}

}