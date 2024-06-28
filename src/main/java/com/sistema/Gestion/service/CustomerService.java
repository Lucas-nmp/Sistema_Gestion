/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistema.Gestion.service;

import com.sistema.Gestion.model.Customer;
import com.sistema.Gestion.repository.CustomerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lucas
 */
@Service
public class CustomerService implements ICustomerService{

    @Autowired
    private CustomerRepository customerRepository;
    
    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
        
    }

    @Override
    public Customer getCustomerById(Integer customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        return customer;
    }

    @Override
    public void addModifyCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        customerRepository.delete(customer);
    }
    
}
