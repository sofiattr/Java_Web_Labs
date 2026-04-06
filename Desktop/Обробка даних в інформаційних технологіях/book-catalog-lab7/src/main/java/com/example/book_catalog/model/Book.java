package com.example.book_catalog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data // Автоматично генерує всі геттери, сеттери, toString() та equals() [cite: 56, 71, 123]
@NoArgsConstructor // Автоматично генерує порожній конструктор [cite: 57, 124]
@AllArgsConstructor // Автоматично генерує конструктор з усіма аргументами [cite: 58, 125]
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String description;

    @Column(name = "image_url")
    private String imageUrl;
}