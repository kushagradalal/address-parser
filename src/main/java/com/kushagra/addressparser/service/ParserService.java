package com.kushagra.addressparser.service;

import com.kushagra.addressparser.model.Address;

public interface ParserService {

	Address getAddress(String address);

	String getStreet(String address);

	String getCity(String address, String stateCode);

	String getState(String address);

	String getZipCode(String address);

}
