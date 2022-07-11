package com.example.demoproject.dao;

import com.example.demoproject.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CustomerRepository extends MongoRepository<Customer, Integer> {

    public Customer findByFirstName(String firstName);
    public List<Customer> findByLastName(String lastName);

}
