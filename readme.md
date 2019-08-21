## Proyecto ejemplo para demostrar el uso de la clase webClient

La clase `WebServerApplication` ejecuta una nueva instancia de SpringBoot en el puerto 8081 gracias a las lineas:

	new SpringApplicationBuilder(WebServerApplication.class).
				properties(Collections.singletonMap("server.port", "8081")).run(args);
				

Ejemplo de una llamada a la función `testURLs` de la clase `clientController`

	$ curl  -s -XPOST http://localhost:8080/client  -H 'Content-Type: application/json' -d'{"aa": "bbx"}'
	the server sayd: {aa=bbx}
	Headers: content-length:12
	Headers: aa:bbx
	Headers: accept-encoding:gzip
	Headers: Content-Type:application/json
	Headers: accept:*/*
	Headers: user-agent:ReactorNetty/0.9.0.M3
	Headers: host:localhost:8080
	
Llamada a función `testGet`  de la clase `clientController`

	$ curl  -s  http://localhost:8080/client/STOP
	All OK. Seconds elapsed: 5.006
	
	
	