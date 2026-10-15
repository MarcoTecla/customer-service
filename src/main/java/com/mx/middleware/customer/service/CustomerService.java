
package com.mx.middleware.customer.service;

import com.mx.middleware.customer.entity.Customer;
import com.mx.middleware.customer.exception.ResourceNotFoundException;
import com.mx.middleware.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer create(Customer customer) {
        if (repository.existsByEmail(customer.getEmail())) {
            throw new IllegalArgumentException(
                    "Ya existe un cliente con ese correo");
        }

        customer.setId(null);
        return repository.save(customer);
    }

    @Transactional(readOnly = true)
    public List<Customer> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Customer findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Cliente no encontrado: " + id));
    }

    public Customer update(Long id, Customer input) {
        Customer customer = findById(id);

        boolean emailUsedByAnotherCustomer =
                repository.existsByEmail(input.getEmail())
                && !customer.getEmail().equalsIgnoreCase(input.getEmail());

        if (emailUsedByAnotherCustomer) {
            throw new IllegalArgumentException(
                    "El correo ya pertenece a otro cliente");
        }

        customer.setName(input.getName());
        customer.setEmail(input.getEmail());

        return repository.save(customer);
    }

    public void delete(Long id) {
        Customer customer = findById(id);
        repository.delete(customer);
    }
}
