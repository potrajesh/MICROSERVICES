package eurekaserver.eurekaserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignClientRestController {
	
	@Autowired
	private ServiceAClient serviceAClient;

	@GetMapping("/fromfeignclient")
	public String helloWorld() {
		return serviceAClient.helloWorld();
	}

}