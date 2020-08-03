package cz.xentalib.service;

import cz.xentalib.dao.BaseDao;
import cz.xentalib.dao.BookDao;
import cz.xentalib.dto.StoreBookDto;
import cz.xentalib.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Object wich implemetns Book Service
 */
@Service
public class BookService extends BaseService<Book> {

    @Autowired
    private BookDao bookDao;

    @Override
    protected BaseDao<Book> getPrimaryDao() {
        return bookDao;
    }

    /**
     * Store new book in DB
     * @param bookDto
     */
    @Transactional
    public void store(StoreBookDto bookDto) {
        Book book = new Book();
        book.setName(bookDto.getName());
        book.setPrice(bookDto.getPrice());
        book.setAuthor(bookDto.getAuthor());
        book.setRating(bookDto.getAmmount());
        persist(book);
    }

    /**
     * Return book with given name
     * @param name
     * @return
     */
    public Book findByName(String name) {
        return bookDao.findByName(name);
    }
}
