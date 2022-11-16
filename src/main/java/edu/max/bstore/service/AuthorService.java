package edu.max.bstore.service;

import edu.max.bstore.dto.Author;
import edu.max.bstore.dto.Book;
import edu.max.bstore.entity.AuthorEntity;

import java.util.List;

public interface AuthorService {
    List<Book> getAllBooksByAuthorName(String name);
    AuthorEntity saveIfNotExists(String name);

}
