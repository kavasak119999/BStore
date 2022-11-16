package edu.max.bstore.service;

import edu.max.bstore.dto.Book;
import edu.max.bstore.entity.BookEntity;
import edu.max.bstore.enumeration.Category;
import edu.max.bstore.exception.BookNotFoundException;
import edu.max.bstore.repository.BookRepository;
import edu.max.bstore.tools.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public Book getBookById(String id) {
        BookEntity bookEntity = bookRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new BookNotFoundException("Book not found, id - '" + id + "'"));
        return EntityToDto.bookEntityToDto(bookEntity);
    }

    @Override
    public Set<Book> getBooksBySearchParameter(String par) {
        List<Book> bookEntityList = bookRepository
                .getAllByNameContains(par).stream()
                .map(EntityToDto::bookEntityToDto)
                .toList();
        List<Book> booksListByAuthorNameSearch = authorService.getAllBooksByAuthorName(par);


        return Stream.concat(bookEntityList.stream(),
                booksListByAuthorNameSearch.stream()).collect(Collectors.toSet());
    }

    @Override
    public List<Book> getBooksByCategory(String category) {
        return bookRepository.getAllByCategory(category).stream()
                .map(EntityToDto::bookEntityToDto)
                .toList();
    }

    @Override
    public Page<BookEntity> getAllBooks(PageRequest pageRequest) {
        return bookRepository.findAll(pageRequest);
    }

    @Override
    public void addBook(BookEntity book) {
        bookRepository.save(book);
    }
}
