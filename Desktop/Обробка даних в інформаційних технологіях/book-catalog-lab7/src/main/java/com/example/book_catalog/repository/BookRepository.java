package com.example.book_catalog.repository;

import com.example.book_catalog.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "books")
public interface BookRepository extends JpaRepository<Book, Long> {
}