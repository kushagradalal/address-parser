package com.kushagra.addressparser;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.kushagra.addressparser.config.Config;

@SpringBootApplication
public class AddressParserApplication {

	public static void main(String[] args) {
		//SpringApplication.run(AddressParserApplication.class, args);

		var ctx = new AnnotationConfigApplicationContext(AddressParserApplication.class);
		System.out.println(ctx.getBean(Config.class).getParserService().getAddress("Seals Construction, Inc. P O Box 66707 Visalia, CA 93290"));
		

	}

}