## Proyecto ejemplo para demostrar el uso de la clase webClient

La clase `WebServerApplication` ejecuta una nueva instancia de SpringBoot en el puerto 8081 gracias a las lineas:

	new SpringApplicationBuilder(WebServerApplication.class).
				properties(Collections.singletonMap("server.port", "8081")).run(args);
				
