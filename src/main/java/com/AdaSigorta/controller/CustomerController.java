package com.AdaSigorta.controller;

import com.AdaSigorta.entity.Customer;
import com.AdaSigorta.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/customers")

public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer>getALlCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer>getCustomerById(@PathVariable Long id){
        Optional<Customer>customer=customerService.getCustomerById(id);
        return customer.map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping("/tc/{tcKimlikNo}")
    public ResponseEntity<Customer>getCustomerbyTcKimlikNo(@PathVariable String tcKimlikNo){
        Optional<Customer>customer = customerService.getCustomerByTcKimlikNo(tcKimlikNo);
        return customer.map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public List<Customer>getCustomerByName(@PathVariable String name){
        return customerService.getCustomerByName(name);
    }
    @PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.saveCustomer(customer);
        return ResponseEntity.status(201).body(savedCustomer);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails){
        Optional<Customer>customer=customerService.getCustomerById(id);
        if (customer.isPresent()){
            Customer updateCustomer=customer.get();
            updateCustomer.setName(customerDetails.getName());
            updateCustomer.setSurname(customerDetails.getSurname());
            updateCustomer.setTcKimlikNo(customerDetails.getTcKimlikNo());
            updateCustomer.setTelNo(customerDetails.getTelNo());
            updateCustomer.setEmail(customerDetails.getEmail());
            updateCustomer.setIl(customerDetails.getIl());
            updateCustomer.setIlce(customerDetails.getIlce());
            updateCustomer.setDogumTarihi(customerDetails.getDogumTarihi());

            return ResponseEntity.ok(customerService.saveCustomer(updateCustomer));

        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void>deleteCustomer(@PathVariable Long id){
        if (customerService.getCustomerById(id).isPresent()){
            customerService.deleteCustomer(id);
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
