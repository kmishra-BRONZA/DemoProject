package com.example.demoproject.api.controller;

import com.example.demoproject.dao.BookRepository;
import com.example.demoproject.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class BookController {
    @Autowired
    private BookRepository repository;

    @PostMapping("/addbook")
    public String saveBook(@RequestBody Book book){
//        final UUID uuid = UUID.fromString("mongoobj");
        book.setId(book.getId());
        repository.save(book);
        return "Book data saved successfully ";
    }

    @GetMapping("/findallbook")
    public List<Book> getAllBook(){
        return repository.findAll();
    }

    @GetMapping("/getbookbyid/{id}")
    public Optional<Book> getBookById(@PathVariable int id){
        return repository.findById(id);
    }

    @DeleteMapping("/deletebyid/{id}")
    public String deleteBookById(@PathVariable int id){
        repository.deleteById(id);
        return "Record deleted successfully for id : "+id;

    }
}
