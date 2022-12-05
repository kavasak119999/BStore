package edu.max.bstore.controller;

import edu.max.bstore.dto.Book;
import edu.max.bstore.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private final BookService bookService;

    public AdminController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/add-book")
    public String addBook() {
        return "pages/add-book";
    }

    @PostMapping(value = "/add-new-book")
    public String addBook(
            @RequestParam String name,
            @RequestParam String authorName,
            @RequestParam String category,
            @RequestParam String title,
            @RequestParam Double price,
            @RequestParam MultipartFile image,
            @RequestParam Integer count) {

        Book book = Book.builder()
                .name(name)
                .title(title)
                .author(authorName)
                .category(category)
                .price(price)
                .count(count)
                .build();

        bookService.addBook(book, image);
        return "redirect:/admin/add-book";
    }


}
