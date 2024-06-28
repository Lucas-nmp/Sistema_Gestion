package com.sistema.Gestion.service;

import com.sistema.Gestion.model.Customer;
import java.util.List;

/**
 *
 * @author Lucas
 */
public interface ICustomerService {
    
    public List<Customer> getAllCustomers();
    
    public Customer getCustomerById(Integer customerId);
    
    public void addModifyCustomer(Customer customer);
    
    public void deleteCustomer(Customer customer);
    
}
