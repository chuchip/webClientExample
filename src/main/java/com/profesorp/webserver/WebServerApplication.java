package com.profesorp.webserver;

import java.util.Collections;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class WebServerApplication {
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(WebServerApplication.class).
				properties(Collections.singletonMap("server.port", "8081")).run(args);
	}

	
}
