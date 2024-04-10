package org.example.javaprojectspringapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

/**
 * Main Controller Class - RESTful API
 * This class handles the requests related to authors and books.
 */
@RestController
@RequestMapping(path="api/v1")
public class MainController {

    public static final String BOOK = "/books";

    public static final String AUTHORS = "/authors";

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    /**
     * Get Request - All Books
     * @return JSON of all Books
     */
    @GetMapping(path = BOOK)
    @CrossOrigin(origins = "*")
    public @ResponseBody
    Iterable<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    /**
     * Get Request - Book With ISBN
     * @param isbn
     * @return JSON of Book with matching ISBN
     */
    @GetMapping(path = BOOK+"/{isbn}")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    Book getBookWithId(@PathVariable String isbn){
        return bookRepository.findByIsbn(isbn);
    }

    /**
     * Post Request - Add New Book
     * @param isbn
     * @param title
     * @param editionNumber
     * @param copyright
     * @param author_id
     * @return Saved if successful, Author not found if not
     */
    @PostMapping(path = BOOK)
    @CrossOrigin(origins = "*")
    public @ResponseBody
    String addNewBook(@RequestParam String isbn, @RequestParam String title, @RequestParam int editionNumber, @RequestParam String copyright, @RequestParam Integer author_id){
        Book book = new Book();
        book.setIsbn(isbn);
        book.setTitle(title);
        book.setEditionNumber(editionNumber);
        book.setCopyright(copyright);
        Optional<Author> author = authorRepository.findById(author_id);
        if(author.isPresent()){
            book.getAuthorList().add(author.get());
            bookRepository.save(book);
            return "Saved";
        }
        return "Author not found";
    }

    /**
     * Get Request - All Authors
     * @return JSON of All Authors
     */
    @GetMapping(path = AUTHORS)
    @CrossOrigin(origins = "*")
    public @ResponseBody
    Iterable<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    /**
     * Get Request - Author with ID
     * @param author_id
     * @return Author with matching ID
     */
    @GetMapping(path = AUTHORS+"/{author_id}")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    Optional<Author> getAuthorWithId(@PathVariable Integer author_id){
        return authorRepository.findById(author_id);
    }

    /**
     * Post Request - Add New Author
     * @param firstName
     * @param lastName
     * @return Saved if successful
     */
    @PostMapping(path = AUTHORS)
    @CrossOrigin(origins = "*")
    public @ResponseBody
    String addNewAuthor(@RequestParam String firstName, @RequestParam String lastName){
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        authorRepository.save(author);
        return "Saved";
    }

    /**
     * Put Request - Update Author
     * @param author_id
     * @param firstName
     * @param lastName
     * @return Author updated if successful, Author not found if not
     */
    @PutMapping(path = AUTHORS+"/{author_id}")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    String updateAuthor(@PathVariable Integer author_id, @RequestParam String firstName, @RequestParam String lastName){
        Optional<Author> author = authorRepository.findById(author_id);
        if(author.isPresent()){
            Author updatedAuthor = author.get();
            updatedAuthor.setFirstName(firstName);
            updatedAuthor.setLastName(lastName);
            authorRepository.save(updatedAuthor);
            return "Author Updated";
        }
        return "Author not found";
    }

    /**
     * Delete Request - Delete Author
     * @param author_id
     * @return Deleted if successful, Author not found if not
     */
    @DeleteMapping(path = AUTHORS+"/{author_id}")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    String deleteAuthor(@PathVariable Integer author_id){
        Optional<Author> author = authorRepository.findById(author_id);
        if(author.isPresent()){
            authorRepository.delete(author.get());
            return "Author Deleted";
        }
        return "Author not found";
    }

    /**
     * Put Request - Update Book
     * @param isbn
     * @param copyright
     * @param edition_number
     * @param title
     * @return Book Updated if successful, Book not found if not
     */
    @PutMapping(path = BOOK+"/{isbn}")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    String updateBook(@PathVariable String isbn, @RequestParam String copyright, @RequestParam Integer edition_number, @RequestParam String title){
        Optional<Book> book = Optional.ofNullable(bookRepository.findByIsbn(isbn));
        if(book.isPresent()){
            Book updatedBook = book.get();
            updatedBook.setCopyright(copyright);
            updatedBook.setEditionNumber(edition_number);
            updatedBook.setTitle(title);
            bookRepository.save(updatedBook);
            return "Book Updated";
        }
        return "Book not found";
    }

    /**
     * Delete Request - Delete Book
     * @param isbn
     * @return Book Deleted if successful, Book Not Found if not
     */
    @DeleteMapping(path = BOOK+"/{isbn}")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    String deleteBook(@PathVariable String isbn){
        Optional<Book> book = Optional.ofNullable(bookRepository.findByIsbn(isbn));
        if(book.isPresent()){
            bookRepository.delete(book.get());
            return "Book Deleted";
        }
        return "Book not found";
    }
}
