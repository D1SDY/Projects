package cz.xentalib.rest;

import cz.xentalib.dto.StoreBookDto;
import cz.xentalib.model.Book;
import cz.xentalib.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Object wich represent Book controller
 */
@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    /**
     * return list of all books
     * @return
     */
    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.findAll();
    }

    /**
     * Method wich add new auction to DB
     * @param bookDto
     * @return
     */
    @PostMapping
    public String storeBook(@RequestBody StoreBookDto bookDto){
       bookService.store(bookDto);
       return "ok";
    }

    /**
     * Method wich represents end point /{id} and returns book with given id{id}
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Integer id){
        return bookService.find(id);
    }

    /**
     * Method wich represents endpoint /name-{name} and returns book with given name:name
     * @param name
     * @return
     */
    @GetMapping("/name-{name}")
    public Book getBookByName(@PathVariable String name){
        return bookService.findByName(name);
    }
}
