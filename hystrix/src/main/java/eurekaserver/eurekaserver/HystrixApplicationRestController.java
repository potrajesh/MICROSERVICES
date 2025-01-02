package eurekaserver.eurekaserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class HystrixApplicationRestController {
	
	
	private Logger log= LoggerFactory.getLogger(HystrixApplicationRestController.class);
	public String getFallBack() {
		return helloWorld("");
	}
	
	@GetMapping("/from-fault-tolerance")
	@CircuitBreaker(name="sample-api",fallbackMethod = "getDataFromString")
	public String helloWorld(String str) {
		log.info("sample api called");
		if(str==null||str.isEmpty()) {
		 throw new RuntimeException("getting error");
		}
		return null;
	}
	
	public String getData(Exception ex) {
		return "return from string data";
	}
	public String getDataFromString(Exception ex) {
		return "getDataFromString";
	}
	

}