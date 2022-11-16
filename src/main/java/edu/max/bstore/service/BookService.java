package edu.max.bstore.service;

import edu.max.bstore.dto.Book;
import edu.max.bstore.entity.BookEntity;
import edu.max.bstore.enumeration.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Set;

public interface BookService {
    Book getBookById(String id);
    List<Book> getBooksByCategory(String category);
    Page<BookEntity> getAllBooks(PageRequest pageRequest);
    void addBook(BookEntity book);
    Set<Book> getBooksBySearchParameter(String param);
}
