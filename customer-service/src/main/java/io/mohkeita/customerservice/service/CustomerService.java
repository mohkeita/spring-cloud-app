package io.mohkeita.customerservice.service;

import io.mohkeita.customerservice.dto.CustomerRequest;
import io.mohkeita.customerservice.exception.CustomerNotFoundException;
import io.mohkeita.customerservice.model.Customer;
import io.mohkeita.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }


    public Customer getCustomerById(Long id) {
        Optional<Customer> requestCustomer = customerRepository.findById(id);

        if (requestCustomer.isEmpty()) {
            throw new CustomerNotFoundException(String.format("Customer with id: '%s' not found ", id));
        }
        return requestCustomer.get();
    }

    @Transactional
    public Customer updateCustomer(Long id, CustomerRequest customerToUpdateRequest) {

        Optional<Customer> customerFromDatabase = customerRepository.findById(id);

        if (customerFromDatabase.isEmpty()) {
            throw new CustomerNotFoundException(String.format("Customer with id: '%s' not found ", id));
        }

        Customer customerToUpdate = customerFromDatabase.get();

        customerToUpdate.setName(customerToUpdateRequest.getName());
        customerToUpdate.setEmail(customerToUpdateRequest.getEmail());

        return customerToUpdate;
    }


    public Long saveCustomer(CustomerRequest customerRequest) {
        Customer customer = new Customer();
        customer.setName(customerRequest.getName());
        customer.setEmail(customerRequest.getEmail());

        customer = customerRepository.save(customer);

        return customer.getId();
    }


    public void removeCustomerById(Long id) {
        customerRepository.deleteById(id);
    }
}
