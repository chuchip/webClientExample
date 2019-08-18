package com.profesorp.webclient.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.profesorp.dto.Customer;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController()
@RequestMapping("/client/")
@Slf4j
public class ClientController {
	final String urlServer="http://localhost:8081/server/";
	
	@GetMapping("{param}")
	public Mono<ResponseEntity<Mono<String>>> testGet(@PathVariable String param) {
		final long dateStarted = System.currentTimeMillis();
		 
		log.debug("Client: en testGest-1");
		WebClient webClient= WebClient.create(urlServer);		
		Mono<ClientResponse> respuesta=webClient.get().uri("?queryParam={name}", param)
				.exchange();		
		
		log.debug("Client: After calling the server");
		
		WebClient webClient2= WebClient.create(urlServer);
		Mono<ClientResponse> respuesta1=webClient2.get().uri("?queryParam={name}", "STOP")
				.exchange();	
		log.debug("llamada 2");
		
		log.debug("Client: Antes del zip");		
				
		
		Mono<ResponseEntity<Mono<String>>> f1=Mono.zip(respuesta,respuesta1)
				.map( t -> {
					log.debug("Processing Map...");
					if (!t.getT1().statusCode().is2xxSuccessful() )
					{
//						return "Error to read T1: Codigo HTTP: "+t.getT1().statusCode();
						return ResponseEntity.status(t.getT1().statusCode()).body(t.getT1().bodyToMono(String.class));
													
					}
					if (!t.getT2().statusCode().is2xxSuccessful() )
					{
//						return "Error to read T2: Codigo HTTP: "+t.getT1().statusCode();
						return		ResponseEntity.status(t.getT2().statusCode()).body(t.getT2().bodyToMono(String.class));								
					}
					log.debug("Todo ok");
					return ResponseEntity.ok().body(Mono.just("All OK. Seconds elapsed: "+ 
					 (  ((double) (System.currentTimeMillis() - dateStarted)/ 1000) )));
				});			
		
//		
//		
		return f1;
	}
	
	@Data
	class MiEstado 
	{
		boolean finalizado1=false;
		boolean finalizado2=false;
		ResponseEntity<Mono<String>> responseEntity;
	}
}
