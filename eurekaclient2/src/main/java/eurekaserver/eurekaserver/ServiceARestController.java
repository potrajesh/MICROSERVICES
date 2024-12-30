package eurekaserver.eurekaserver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceARestController {

	@GetMapping("/fromeurekaclient2")
	public String helloWorld() {
		return "Hello world fromeurekaclient2 !";
	}

}