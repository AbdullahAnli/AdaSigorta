package com.AdaSigorta.service;

import com.AdaSigorta.entity.Customer;
import com.AdaSigorta.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer>getAllCustomers(){
        return customerRepository.findAll();
    }
    public Optional<Customer>getCustomerById(Long id){
        return customerRepository.findById(id);
    }
    public Optional<Customer>getCustomerByTcKimlikNo(String tcKimlikNo){
        return customerRepository.findByTcKimlikNo(tcKimlikNo);

    }
    public List<Customer>getCustomerByName(String name){
        return customerRepository.findByName(name);
    }
    public Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }
    public void deleteCustomer(Long id){
        customerRepository.deleteById(id);
    }
}
