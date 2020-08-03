package cz.xentalib.dao;

import cz.xentalib.model.Order;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Class wich represents OrderDao where we implemented all queries
 */
@Repository
public class OrderDao extends BaseDao<Order> {

    public OrderDao() {
        super(Order.class);
    }

    /**
     * Query for find all user orders
     * @param id
     * @return
     */
    public List<Order> findAllForUser(Integer id) {
        return em.createNamedQuery("Order.findAllForUser", Order.class).setParameter("id", id)
                .getResultList();
    }
}
