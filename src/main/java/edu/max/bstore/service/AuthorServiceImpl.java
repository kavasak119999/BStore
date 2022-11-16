package edu.max.bstore.service;

import edu.max.bstore.dto.Author;
import edu.max.bstore.dto.Book;
import edu.max.bstore.entity.AuthorEntity;
import edu.max.bstore.entity.BookEntity;
import edu.max.bstore.repository.AuthorRepository;
import edu.max.bstore.tools.DtoToEntity;
import edu.max.bstore.tools.EntityToDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorEntity saveIfNotExists(String name) {
        AuthorEntity authorEntity = authorRepository.findByName(name);

        if (authorEntity == null) {
            authorEntity = AuthorEntity.builder()
                    .name(name)
                    .build();
            authorRepository.save(authorEntity);
        }
//jpql
        return authorEntity;
    }

    @Override
    public List<Book> getAllBooksByAuthorName(String name) {
        List<AuthorEntity> authorEntityList = authorRepository.getAllByNameContains(name);
        List<Author> authorList = authorEntityList.stream()
                .map(EntityToDto::authorEntityToDto)
                .toList();

        Stream<List<Book>> listStreamOfBooks = authorList.stream()
                .map(author -> new ArrayList<>(author.getBookList()));

        Set<Book> books = listStreamOfBooks.flatMap(List::stream)
                .collect(Collectors.toSet());
        return books.stream().toList();
    }


}
