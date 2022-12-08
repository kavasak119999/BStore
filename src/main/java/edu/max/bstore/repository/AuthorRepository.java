package edu.max.bstore.repository;


import edu.max.bstore.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, UUID> {
    AuthorEntity findByName(String name);

    List<AuthorEntity> getAllByNameContains(String name);
}
