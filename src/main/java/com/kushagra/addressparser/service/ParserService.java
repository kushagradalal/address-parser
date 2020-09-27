package com.kushagra.addressparser.service;

import com.kushagra.addressparser.model.Address;

public interface ParserService {

	Address getAddress(String address);

	String getStreet(String address);

	String getCity(String address, String stateCode);

	String getStateCode(String address);

	String getState(String stateCode);

	String getZipCode(String address);

	String getPoBox(String address);

}
