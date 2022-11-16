package edu.max.bstore.repository;

import edu.max.bstore.dto.Image;
import edu.max.bstore.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends JpaRepository<ImageEntity, String> {

}