package com.example.book_catalog_lab5.controller; // Перевірте назву пакета!

import com.example.book_catalog_lab5.model.Book;
import com.example.book_catalog_lab5.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books") // Це головна адреса нашого сайту [cite: 216-217]
public class BookController {

    @Autowired
    private BookRepository repository; // Підключаємо базу даних [cite: 219-220]

    // 1. Отримання всіх книг (Read) [cite: 222-225]
    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", repository.findAll()); // Передаємо список книг у HTML
        return "book-list"; // Програма шукатиме файл book-list.html
    }

    // 2. Відкриття форми для додавання нової книги
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book()); // Передаємо порожній об'єкт для заповнення
        return "book-form"; // Програма шукатиме файл book-form.html
    }

    // 3. Збереження книги в базу даних (Create / Update)
    @PostMapping("/save")
    public String saveBook(@ModelAttribute("book") Book book) {
        repository.save(book); // Зберігаємо магією Hibernate
        return "redirect:/books"; // Оновлюємо сторінку (перекидаємо на головну)
    }

    // Оновлення книги (Update) - відкриває форму з існуючими даними
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Book book = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Невірний ID книги: " + id));
        model.addAttribute("book", book);
        return "book-form"; // Використовуємо ту саму форму!
    }

    // 4. Видалення книги (Delete)
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/books";
    }
}