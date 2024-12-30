package eurekaserver.eurekaserver;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "eurekaclient")  // Name of the Eureka registered service
public interface ServiceAClient {

    @GetMapping("/helloWorld")  // Mapping to the endpoint you want to call
    String helloWorld();
}