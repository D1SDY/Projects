package cz.xentalib.dao;

import cz.xentalib.model.Book;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
/**
 * Class wich represents BookDao where we implemented all queries
 */
@Repository
public class BookDao extends BaseDao<Book> {

    public BookDao() {
        super(Book.class);
    }

    /**
     * Query for find book by name
     * @param name
     * @return
     */
    public Book findByName(String name) {
        try {
            return em.createNamedQuery("Book.findByName", Book.class).setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
