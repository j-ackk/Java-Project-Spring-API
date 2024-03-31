package org.example.javaprojectspringapi;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Book Class
 * Manages Book entities in the database
 */
@Entity(name = "titles")
public class Book {
    @Id
    private String isbn;
    private String title;

    private int editionNumber;
    private String copyright;
    @ManyToMany
    @JoinTable(
            name = "author_isbn",
            joinColumns = @JoinColumn(name = "isbn"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )

    private List<Author> authorList = new ArrayList<>();

    /**
     * Set ISBN
     * @param isbn
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Set Title
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Set Edition Number
     * @param editionNumber
     */
    public void setEditionNumber(int editionNumber) {
        this.editionNumber = editionNumber;
    }

    /**
     * Set Copyright
     * @param copyright
     */
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    /**
     * Get the ISBN
     * @return isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Get the title
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get the edition number as an integer
     * @return edition number
     */
    public int getEditionNumber() {
        return editionNumber;
    }

    /**
     * Get the copyright
     * @return copyright
     */
    public String getCopyright() {
        return copyright;
    }

    /**
     * Get AuthorList
     * @return list of Author objects
     */
    public List<Author> getAuthorList() {
        return authorList;
    }

    /**
     * Set AuthorList
     * @param authorList
     */
    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }
}
