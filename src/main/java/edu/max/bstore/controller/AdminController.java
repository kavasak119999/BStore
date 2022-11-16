package edu.max.bstore.controller;

import edu.max.bstore.entity.AuthorEntity;
import edu.max.bstore.entity.BookEntity;
import edu.max.bstore.entity.ImageEntity;
import edu.max.bstore.service.AuthorService;
import edu.max.bstore.service.BookService;
import edu.max.bstore.service.ImageService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final ImageService imageService;

    public AdminController(BookService bookService, AuthorService authorService, ImageService imageService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.imageService = imageService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/add_book")
    public String addBook() {
        return "/pages/add_book";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/add_new_book")
    public String addBook(
            @RequestParam String name,
            @RequestParam String authorName,
            @RequestParam String category,
            @RequestParam String title,
            @RequestParam Double price,
            @RequestParam MultipartFile image,
            @RequestParam Integer count) {
        AuthorEntity authorEntity = authorService.saveIfNotExists(authorName);

        ImageEntity imageEntity = imageService.saveImage(image.getOriginalFilename());

        if (authorEntity.getBookEntityList() == null) {
            authorEntity.setBookEntityList(new ArrayList<>());
        }

        BookEntity bookEntity = BookEntity.builder()
                .name(name)
                .author(authorEntity)
                .title(title)
                .category(category)
                .image(imageEntity)
                .price(price)
                .count(count)
                .build();

        authorEntity.getBookEntityList().add(bookEntity);
        imageService.saveImageToVolumeStorage(image);

        bookService.addBook(bookEntity);
        return "redirect:/admin/add_book";
    }


}
