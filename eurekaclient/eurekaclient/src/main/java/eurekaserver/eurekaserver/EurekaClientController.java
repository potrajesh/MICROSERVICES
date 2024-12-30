package eurekaserver.eurekaserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EurekaClientController {
	
	@Autowired
	private Environment env;

	@GetMapping("/helloWorld")
	public String helloWorld() {
		return "Hello world from eureka Client 1 !"+"using port number is :"+env.getProperty("server.port");
	}

}