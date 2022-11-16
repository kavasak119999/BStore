package edu.max.bstore.repository;


import edu.max.bstore.entity.AuthorEntity;
import edu.max.bstore.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<AuthorEntity, UUID> {
    AuthorEntity findByName(String name);
    List<AuthorEntity> getAllByNameContains(String name);
}
