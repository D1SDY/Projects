package cz.xentalib.repository;

import cz.xentalib.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * UserRepository wich extends JpaRepositiry for automatical queries creation
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByLogin(String login);

    User findUserByEmail(String email);

    List<User> findAll();
}
