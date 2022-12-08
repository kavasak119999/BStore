package edu.max.bstore.service;

import edu.max.bstore.dto.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;


public interface BookService {
    Book getBookById(String id);

    Page<Book> getBooksByCategory(String category, PageRequest pageRequest);

    Page<Book> getAllBooks(PageRequest pageRequest);

    void addBook(Book book, MultipartFile image);

    Page<Book> getBooksBySearchParameter(String param, PageRequest pageRequest);
}
