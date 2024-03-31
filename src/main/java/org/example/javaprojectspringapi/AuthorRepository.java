package org.example.javaprojectspringapi;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * AuthorRepository Class
 * Extends CrudRepository for basic CRUD operations
 */
public interface AuthorRepository extends CrudRepository<Author, Integer> {
    Optional<Author> findById(Integer id);
}