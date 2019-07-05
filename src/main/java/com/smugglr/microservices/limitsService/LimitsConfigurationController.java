package com.smugglr.microservices.limitsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.smugglr.microservices.limitsService.bean.LimitConfiguration;

@RestController
public class LimitsConfigurationController {

	@Autowired
	Configuration configuration;

	@GetMapping("/limits")
	public LimitConfiguration retreiveLimitsFromConfiguration() {

		return new LimitConfiguration(configuration.getMaximum(), configuration.getMinimum());
	}
	
	@GetMapping("/fault-tolerance-example")
	@HystrixCommand(fallbackMethod="fallbackRetrieveConfiguration")
	public LimitConfiguration retrieveConfiguration() {
		throw new RuntimeException("Not Available ");
	}
	
	
	public LimitConfiguration fallbackRetrieveConfiguration() {
		return new LimitConfiguration(999, 9);	
	}
}
