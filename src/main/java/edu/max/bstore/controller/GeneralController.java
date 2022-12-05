package edu.max.bstore.controller;

import edu.max.bstore.dto.Book;
import edu.max.bstore.dto.Order;
import edu.max.bstore.enumeration.Category;
import edu.max.bstore.service.BookService;
import edu.max.bstore.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.Locale;

@Controller
public class GeneralController {
    private final BookService bookService;
    private final OrderService orderService;

    public GeneralController(BookService bookService, OrderService orderService) {
        this.bookService = bookService;
        this.orderService = orderService;
    }

    @GetMapping(value = "/")
    public ModelAndView showAllBooks(@RequestParam(value = "pages", defaultValue = "1") String page, ModelMap model, Principal principal) {
        Page<Book> books = bookService.getAllBooks(PageRequest.of(Integer.parseInt(page) - 1, 8));

        loadToModel(model, books, page);
        if (principal != null) {
            model.addAttribute("userName", principal.getName());
        }

        return new ModelAndView("pages/BStore", model);
    }

    @GetMapping(value = "/book")
    public ModelAndView showBookById(@RequestParam(name = "id") String id, ModelMap model, Principal principal) {
        Book book = bookService.getBookById(id);

        if (principal != null) {
            model.addAttribute("userName", principal.getName());
        }

        model.addAttribute("book", book);
        return new ModelAndView("pages/book", model);
    }

    @GetMapping(value = "/search")
    public ModelAndView showBooksByName(@RequestParam(value = "pages", defaultValue = "1") String page,
                                        @RequestParam(value = "param") String param,
                                        ModelMap model) {
        Page<Book> books = bookService.getBooksBySearchParameter(param, PageRequest.of(Integer.parseInt(page) - 1, 8));

        loadToModel(model, books, page);
        model.addAttribute("searchValue", param);

        return new ModelAndView("pages/BStore", model);
    }

    @GetMapping(value = "/catalog")
    public ModelAndView showCategoryById(@RequestParam(value = "pages", defaultValue = "1") String page,
                                         @RequestParam(name = "category") String category, ModelMap model) {
        try {
            Category.valueOf(category.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }

        Page<Book> books = bookService.getBooksByCategory(category, PageRequest.of(Integer.parseInt(page) - 1, 6));

        loadToModel(model, books, page);
        model.addAttribute("category", category);

        return new ModelAndView("pages/BStore", model);
    }

    private void loadToModel(ModelMap model, Page<Book> books, String page){
        long totalPages = books.getTotalPages();
        long totalItems = books.getTotalElements();

        model.addAttribute("data", books.stream().toList());
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("number", books.getNumber());
        model.addAttribute("currentPage", page);
    }
}
