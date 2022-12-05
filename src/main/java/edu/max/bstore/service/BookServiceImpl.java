package edu.max.bstore.service;

import edu.max.bstore.dto.Book;
import edu.max.bstore.entity.AuthorEntity;
import edu.max.bstore.entity.BookEntity;
import edu.max.bstore.entity.ImageEntity;
import edu.max.bstore.exception.BookNotFoundException;
import edu.max.bstore.repository.BookRepository;
import edu.max.bstore.tools.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final ImageService imageService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, ImageService imageService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.imageService = imageService;
    }

    @Override
    public Book getBookById(String id) {
        BookEntity bookEntity = bookRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new BookNotFoundException("Book not found, id - '" + id + "'"));
        return EntityToDto.bookEntityToDto(bookEntity);
    }

    @Override
    public Page<Book> getBooksBySearchParameter(String par, PageRequest pageRequest) {
        Page<BookEntity> bookEntities = bookRepository.findAllByNameContains(par, pageRequest);
        return bookEntities.map(EntityToDto::bookEntityToDto);
    }

    @Override
    public Page<Book> getBooksByCategory(String category, PageRequest pageRequest) {
        Page<BookEntity> bookEntities = bookRepository.findAllByCategory(category, pageRequest);
        return bookEntities.map(EntityToDto::bookEntityToDto);
    }

    @Override
    public Page<Book> getAllBooks(PageRequest pageRequest) {
        Page<BookEntity> entities = bookRepository.findAll(pageRequest);

        return entities.map(EntityToDto::bookEntityToDto);
    }

    @Override
    public void addBook(Book book, MultipartFile image) {
        AuthorEntity authorEntity = authorService.saveIfNotExists(book.getAuthor());
        ImageEntity imageEntity = imageService.saveImage(image.getOriginalFilename());

        if (authorEntity.getBookEntityList() == null) {
            authorEntity.setBookEntityList(new ArrayList<>());
        }

        BookEntity bookEntity = BookEntity.builder()
                .name(book.getName())
                .category(book.getCategory())
                .author(authorEntity)
                .title(book.getTitle())
                .count(book.getCount())
                .price(book.getPrice())
                .image(imageEntity)
                .build();

        authorEntity.getBookEntityList().add(bookEntity);
        imageService.saveImageToVolumeStorage(image);

        bookRepository.save(bookEntity);
    }
}
