package edu.max.bstore.repository;

import edu.max.bstore.entity.AuthorEntity;
import edu.max.bstore.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, UUID> {
     List<BookEntity> getAllByNameContains(String name);
     List<BookEntity> getAllByCategory(String category);
}
