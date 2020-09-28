package com.kushagra.addressparser;

import com.kushagra.addressparser.model.Address;
import com.kushagra.addressparser.service.ParserService;
import com.kushagra.addressparser.service.ParserServiceImpl;

public class AddressParser {

	private static ParserService parser = new ParserServiceImpl();

	private AddressParser() {
	}

	public static Address getAddress(String address) {
		return parser.getAddress(address);
	}

}
