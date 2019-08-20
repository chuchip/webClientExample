package com.profesorp.webserver.controller;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.profesorp.dto.Customer;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/server/")

public class ServerController {
	

	@GetMapping("")
	public ResponseEntity<Customer> getCustomer(@RequestParam(required = false) String queryParam) {
		log.debug("Received request at getCustomer:" + queryParam);
		
		if (queryParam == null  || "NULL".equals(queryParam))
			throw new RuntimeException("Give me a customer!");
		if (queryParam.equals("STOP"))
		{
			try {
				Thread.sleep(5000);
			} catch (Exception k) {k.printStackTrace();}
		}
		Customer customer = new Customer();
		customer.setName("Customer " + queryParam);
		customer.setAddress("Address Customer " + queryParam);
		if (queryParam.equals("ERROR"))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customer);
		if (queryParam.equals("CREATED"))
			return ResponseEntity.status(HttpStatus.CREATED).body(customer);
	
		return ResponseEntity.ok().body(customer);
	}
	
	@PostMapping("post")
	public ResponseEntity<String> postCustomer(@RequestBody Map<String,String> body,ServerHttpRequest  request) {
		
		String s="the server sayd: "+body+"\n";
		for (Entry<String, List<String>> map : request.getHeaders().entrySet())
		{
			log.debug("Headers -> "+map.getKey()+ ":"+map.getValue().get(0));
			s+="Headers: "+map.getKey()+ ":"+map.getValue().get(0)+"\n";
			
		}		
		return ResponseEntity.ok().body(s);
	}
	

	
}
