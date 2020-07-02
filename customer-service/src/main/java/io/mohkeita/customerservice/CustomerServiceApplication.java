package io.mohkeita.customerservice;

import io.mohkeita.customerservice.dto.CustomerRequest;
import io.mohkeita.customerservice.service.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerService customerService) {
		return args -> {
			customerService.saveCustomer(new CustomerRequest("IFAPME", "ifapme@gmail.com"));
			customerService.saveCustomer(new CustomerRequest("HELMO", "helmo@gmail.com"));
			customerService.saveCustomer(new CustomerRequest("ESP", "esp@gmail.com"));
			customerService.getAllCustomers().forEach(System.out::println);
		};
	}

}
