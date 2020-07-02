package io.mohkeita.inventoryservice;

import io.mohkeita.inventoryservice.dto.ProductRequest;
import io.mohkeita.inventoryservice.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ProductService productService) {
		return args -> {
			productService.saveProduct(new ProductRequest("Ord HP 878", 870));
			productService.saveProduct(new ProductRequest("Ord Mac Book Pro", 1200));
			productService.saveProduct(new ProductRequest("Imprimante Epson 32", 700));
		};
	}

}
