package com.kushagra.addressparser.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.kushagra.addressparser.service.ParserService;

@Configuration
public class Config {

	@Autowired
	private ParserService parserService;

	public ParserService getParserService() {
		return parserService;
	}

}
