package com.example.book_catalog.controller;

import com.example.book_catalog.model.Book;
import com.example.book_catalog.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        log.info("Запит на отримання всіх книг");
        return bookRepository.findAll();
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        log.info("Збереження книги: {}", book.getTitle());
        return bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        log.info("Видалення книги з ID: {}", id);
        bookRepository.deleteById(id);
    }
}