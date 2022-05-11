package com.spring.app;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@RequestMapping("api")
@RestController
@AllArgsConstructor
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public Collection<Book> fetchAllBooks(){
        return bookService.findAll();
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBook(@PathVariable(value = "id") String id) {
        Optional<Book> optionalBook = bookService.getById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            return new ResponseEntity<>(book, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add_book")
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) {
        if (bookService.add(book) != null){
            return  ResponseEntity.ok(book);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Book> update(@PathVariable(value = "id") String id, @Valid @RequestBody Book bookDetails) {
        Optional<Book> optionalBook = bookService.getById(id);
        if (optionalBook.isPresent()){
            Book book = optionalBook.get();
            book.setStatus(bookDetails.getStatus());
            book.setRating(bookDetails.getRating());
            final Book updatedBook = bookService.update(book);
            return new ResponseEntity<>(updatedBook,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable(value = "id") String id){

        Optional<Book> optionalBook = bookService.getById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            bookService.delete(book);
            return new ResponseEntity<>(id, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
