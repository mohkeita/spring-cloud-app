package io.mohkeita.inventoryservice.service;

import io.mohkeita.inventoryservice.dto.ProductRequest;
import io.mohkeita.inventoryservice.exception.ProductNotFoundException;
import io.mohkeita.inventoryservice.model.Product;
import io.mohkeita.inventoryservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    public Product getProductById(Long id) {
        Optional<Product> requestProduct = productRepository.findById(id);

        if (requestProduct.isEmpty()) {
            throw new ProductNotFoundException(String.format("Product with id: '%s' not found ", id));
        }
        return requestProduct.get();
    }

    @Transactional
    public Product updateCustomer(Long id, ProductRequest productToUpdateRequest) {
        Optional<Product> productFromDatabase = productRepository.findById(id);

        if (productFromDatabase.isEmpty()) {
            throw new ProductNotFoundException(String.format("Product with id: '%s' not found ", id));
        }

        Product productToUpdate = productFromDatabase.get();

        productToUpdate.setName(productToUpdateRequest.getName());
        productToUpdate.setPrice(productToUpdateRequest.getPrice());

        return productToUpdate;
    }

    public Long saveProduct(ProductRequest productRequest) {

        Product product = new Product();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());

        product = productRepository.save(product);

        return product.getId();
    }

    public void removeProductById(Long id) {
        productRepository.deleteById(id);
    }
}
