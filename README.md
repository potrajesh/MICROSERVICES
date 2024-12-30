using this projects we need to do setup for eurekaserver ,eurekaclient,feignclient.

Steps to develop Service Registry Application (Eureka Server)
===============================================================

1) Create SpringBoot application with below dependency

	 - Eureka Server (spring-cloud-starter-netflix-eureka-server)
	 - devtools

2) Configure @EnableEurekaServer annotation in boot start class

3) Configure below properties in application.properties file

- server.port:8761
- spring.application.name=discoverysevice
- eureka.instance.hostname=localhost
- eureka.client.register-with-eureka=false
- eureka.client.service-url.defaultzone=http://${eureka.instance.hostname}:${server.port}/eureka


Note-1: If "Service-Registry" project port is 8761 then clients can discover service-registry and will register automatically with service-registry. 

Note-2 : If service-registry project running on any other port number then we have to register clients with service-registry manually.

4) Once application started we can access Eureka Dashboard using below URL

		URL : http://localhost:8761/
======================================
Steps to develop Eureka client

1) Create SpringBoot application with below dependency

	 - Eureka client (spring-cloud-starter-netflix-eureka-client)
	 - devtools

2) Configure @EnableDiscoveryClient annotation in boot start class

3) Configure below properties in application.properties file

application.properties:

- server.port:8090
- spring.application.name=eurekaclient
- eureka.instance.hostname=localhost
- eureka.client.register-with-eureka=true
- eureka.client.service-url.defaultzone=http://localhost:8761/eureka/


Note-1: If "Service-Registry" project port is 8761 then clients can discover service-registry and will register automatically with service-registry. 

Note-2 : If service-registry project running on any other port number then we have to register clients with service-registry manually.


4)@RestController
public class ServiceARestController {

	@GetMapping("/helloWorld")
	public String helloWorld() {
		return "Hello world from eureka Client 1 !";
	}

}

5) Once application started we can access Eureka Dashboard using below URL

		URL : http://localhost:8090


======================================
Steps to develop Feign client

To enable inter-service communication between two services using Feign Client, you'll need to follow these steps:

Ensure that both services are registered with Eureka.


1) Create SpringBoot application with below dependency

	 - Eureka client (spring-cloud-starter-netflix-eureka-client)
	 - Feign (spring-cloud-starter-openfeign)
	 - web

2) Configure @EnableFeignClients and @EnableDiscoveryClient annotation in boot main start class

3) Configure below properties in application.properties file

application.properties:

- server.port:8092
- spring.application.name=eurekaclient2
- eureka.instance.hostname=localhost
- eureka.client.register-with-eureka=true
- eureka.client.service-url.defaultzone=http://localhost:8761/eureka/

4) 
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "eurekaclient")  // Name of the Eureka registered service
public interface ServiceAClient {

    @GetMapping("/helloWorld")  // Mapping to the endpoint you want to call
    String helloWorld();
}
5)@RestController
public class ServiceARestController {
	
	@Autowired
	private ServiceAClient serviceAClient;

	@GetMapping("/fromfeignclient")
	public String helloWorld() {
		return serviceAClient.helloWorld();
	}

}
6)Once application started we can access Eureka Dashboard using below URL

URL : http://localhost:8092/fromfeignclient          
//we get data from eurekaclient project endpoint.		
===============================================



		
