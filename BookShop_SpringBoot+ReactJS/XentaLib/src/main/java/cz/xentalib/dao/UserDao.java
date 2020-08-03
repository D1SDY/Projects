package cz.xentalib.dao;

import cz.xentalib.model.Auction;
import cz.xentalib.model.Order;
import cz.xentalib.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Set;
/**
 * Class wich represents UserDao where we implemented all queries
 */
@Repository
public class UserDao extends BaseDao<User> {

    public UserDao() {
        super(User.class);
    }

    /**
     * Query for find user by name
     * @param username
     * @return
     */
    public User findByUsername(String username) {
        try {
            return em.createNamedQuery("User.findByUsername", User.class).setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Query for find user by email
     * @param email
     * @return
     */
    public User findByEmail(String email) {
        try {
            return em.createNamedQuery("User.findByEmail", User.class).setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}