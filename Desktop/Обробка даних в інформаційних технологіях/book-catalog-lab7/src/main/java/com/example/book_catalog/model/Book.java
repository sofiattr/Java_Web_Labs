package com.example.book_catalog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data // Автоматично генерує всі геттери, сеттери, toString() та equals()
@NoArgsConstructor // Автоматично генерує порожній конструктор
@AllArgsConstructor // Автоматично генерує конструктор з усіма аргументами
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