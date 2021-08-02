package org.murugan.orderitemsvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OrderItemServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderItemServiceApplication.class, args);
	}

}
