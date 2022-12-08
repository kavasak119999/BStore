package edu.max.bstore.service;

import edu.max.bstore.entity.AuthorEntity;


public interface AuthorService {
    AuthorEntity saveIfNotExists(String name);
}
