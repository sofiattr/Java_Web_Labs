package com.example.book_catalog.controller;

import com.example.book_catalog.model.Book;
import com.example.book_catalog.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173") // Дозвіл для вашого Vue.js сайту
@RestController // Головна вимога 6-ї лаби [cite: 33, 137]
@RequestMapping("/api/books") // Стандартний REST-шлях [cite: 138]
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    // Отримання всіх книг (GET) [cite: 27, 142-145]
    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Створення нової книги (POST) [cite: 28, 146-150]
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    // Оновлення існуючої книги (PUT) [cite: 29-30, 151-157]
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        Book book = bookRepository.findById(id).orElseThrow();
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setDescription(bookDetails.getDescription());
        book.setImageUrl(bookDetails.getImageUrl());
        return bookRepository.save(book);
    }

    // Видалення книги (DELETE) [cite: 31, 158-161]
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
    }
}