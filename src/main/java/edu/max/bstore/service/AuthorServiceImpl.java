package edu.max.bstore.service;

import edu.max.bstore.entity.AuthorEntity;
import edu.max.bstore.repository.AuthorRepository;
import org.springframework.stereotype.Service;

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
        return authorEntity;
    }
}
