package org.example.javaprojectspringapi;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.List;

/**
 * Author Class
 * Manages Author entities in the database
 */
@Entity(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;

    private String lastName;

    @ManyToMany(mappedBy = "authorList")
    @JsonBackReference
    private List<Book> bookList;

    /**
     * Get Author ID
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Set Author ID
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Get First Name
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set First Name
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get Last Name
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set Last Name
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get Book List
     * @return bookList of Book objects
     */
    public List<Book> getBookList() {
        return bookList;
    }

    /**
     * Set Book List
     * @param bookList
     */
    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
