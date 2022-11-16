package edu.max.bstore.controller;

import edu.max.bstore.dto.Book;
import edu.max.bstore.entity.BookEntity;
import edu.max.bstore.enumeration.Category;
import edu.max.bstore.service.BookService;
import edu.max.bstore.tools.EntityToDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Locale;
import java.util.Set;

@Controller
public class LibraryController {
    private final BookService bookService;

    public LibraryController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/")
    public String getAllBooks(@RequestParam(value = "page", defaultValue = "0", required = false) String page, Model model) {
        Page<BookEntity> books = bookService.getAllBooks(PageRequest.of(Integer.parseInt(page),2));
        long totalPages = books.getTotalPages();
        long totalItems = books.getTotalElements();

        model.addAttribute("data", books.stream().map(EntityToDto::bookEntityToDto).toList());
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("number", books.getNumber());
        model.addAttribute("currentPage", page);
        return "/pages/books";
    }

    @GetMapping(value = "/viewbook")
    public ModelAndView getBookById(@RequestParam(name = "id") String id, ModelMap model) {
        Book book = bookService.getBookById(id);

        model.addAttribute("book", book);
        return new ModelAndView("/pages/books", model);
    }

    @PostMapping(value = "/search")
    public ModelAndView getBooksByName(@RequestParam(required = false) String param, ModelMap model) {
        Set<Book> booksSet = bookService.getBooksBySearchParameter(param);

        model.addAttribute("booksSet", booksSet);
        return new ModelAndView("/pages/books", model);
    }

    @GetMapping(value = "/catalog")
    public ModelAndView getCategoryById(@RequestParam(name = "category") String category, ModelMap map){
        try{
            Category.valueOf(category.toUpperCase(Locale.ROOT));
        } catch(IllegalArgumentException ex){
            ex.printStackTrace();
        }

        List<Book> categoryBooksList = bookService.getBooksByCategory(category);

        map.addAttribute("categoryBooksList", categoryBooksList);

        return new ModelAndView("/pages/catalog", map);
    }
}
