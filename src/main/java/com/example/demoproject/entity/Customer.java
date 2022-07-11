package com.example.demoproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@ToString
//@AllArgsConstructor
public class Customer {
    @Id
    public int id;
    public String firstName;
    public String lastName;

   public Customer(){}

    public Customer(String firstName, String lastName){

       this.firstName=firstName;
       this.lastName=lastName;

    }



}
