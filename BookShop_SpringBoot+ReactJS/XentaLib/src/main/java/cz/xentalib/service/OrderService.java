package cz.xentalib.service;

import cz.xentalib.dao.BaseDao;
import cz.xentalib.dao.OrderDao;
import cz.xentalib.model.Book;
import cz.xentalib.model.Order;
import cz.xentalib.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
/**
 * Object wich implemetns Order Service
 */
@Service
public class OrderService extends BaseService<Order> {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;


    @Override
    protected BaseDao<Order> getPrimaryDao() {
        return orderDao;
    }

    /**
     * Store new order in DB
     * @param id
     * @param user
     */
    @Transactional
    public void store(Integer id, User user) {
        Order order = new Order();
        Book book = bookService.find(id);
        order.setBook(book);
        order.setCustomer(user);
        user.setBalance(user.getBalance() - book.getPrice());
        book.setAmmount(book.getAmmount()-1);
        bookService.update(book);
        userService.update(user);
        persist(order);

    }

    /**
     * Returns list of user's orders
     * @param user
     * @return
     */
    @Transactional
    public List<Order> getAllForUser(User user) {
        return orderDao.findAllForUser(user.getId());
    }
}
