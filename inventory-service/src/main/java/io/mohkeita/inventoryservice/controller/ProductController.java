package io.mohkeita.inventoryservice.controller;

import io.mohkeita.inventoryservice.dto.ProductRequest;
import io.mohkeita.inventoryservice.model.Product;
import io.mohkeita.inventoryservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeProductById(@PathVariable("id") Long id) {

        productService.removeProductById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Void> saveProduct(@Valid @RequestBody ProductRequest productRequest,
                                            UriComponentsBuilder uriComponentsBuilder) {

        Long id = productService.saveProduct(productRequest);

        UriComponents uriComponents = uriComponentsBuilder.path("/products/{id}").buildAndExpand(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id,
                                                 @Valid @RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(productService.updateCustomer(id, productRequest));
    }
}
