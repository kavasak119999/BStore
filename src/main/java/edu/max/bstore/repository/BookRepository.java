package edu.max.bstore.repository;

import edu.max.bstore.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, UUID> {
     Page<BookEntity> findAllByNameContains(String name, Pageable pageable);
     Page<BookEntity> findAllByCategory(String category, Pageable pageable);
}
