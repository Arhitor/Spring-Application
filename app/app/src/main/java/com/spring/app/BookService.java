package com.spring.app;

import com.mongodb.MongoWriteException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }


    public Collection<Book> findAll() {
        return repository.findAll();
    }


    public Book add(Book book) {
        book.setAddedAt(LocalDateTime.now());
//        book.setFinishedAt(Date.valueOf(book.getFinishedAt().toString()));
        try {
            return repository.insert(book);
        }
        catch (MongoWriteException e) {
            return null;
        }
    }


    public Book update(Book book) {
        return repository.save(book);
    }

    public void delete(Book book) {
        repository.delete(book);
    }

    public Optional<Book> getById(String id){
        List<Book> bookList = repository.findAll();
        return bookList.stream().filter(x->x.getId().equals(id)).findFirst();
    }
}
