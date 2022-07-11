package com.example.demoproject.dao;

import com.example.demoproject.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, Integer> {


}
