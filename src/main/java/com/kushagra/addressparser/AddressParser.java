package com.kushagra.addressparser;

import com.kushagra.addressparser.model.Address;
import com.kushagra.addressparser.service.ParserService;
import com.kushagra.addressparser.service.ParserServiceImpl;

public class AddressParser {

	static ParserService parser;

	public static void main(String[] args) {
		parser = new ParserServiceImpl();
	}

	public static Address getAddress(String address) {
		return parser.getAddress(address);
	}

}
