package com.example.CounterClientDemo;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.CloudEurekaClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@EnableScheduling
public class CounterClientDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CounterClientDemoApplication.class, args);
	}

	@Autowired
	private EurekaClient discoveryClient;

	@Bean
	public RestTemplate template(){
		return new RestTemplate();
	}

}
