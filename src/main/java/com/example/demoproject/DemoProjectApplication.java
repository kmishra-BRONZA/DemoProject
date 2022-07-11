package com.example.demoproject;

import com.example.demoproject.dao.CustomerRepository;
import com.example.demoproject.entity.Customer;
import io.mongock.runner.springboot.EnableMongock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableMongock
public class DemoProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoProjectApplication.class, args);
    }

}
