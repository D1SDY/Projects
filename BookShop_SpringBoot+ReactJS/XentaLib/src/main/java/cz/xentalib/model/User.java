package cz.xentalib.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;
/**
 * Object wich represents User Table
 */
@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "User.findByUsername",
                query = "SELECT u FROM User u WHERE u.login = :username"),
        @NamedQuery(name = "User.findByEmail",
                query = "SELECT u FROM User u WHERE u.email=:email"),
})
public class User extends AbstractEntity {

    @Column
    private String login;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private int balance;

    @ManyToMany
    private Set<Auction> auctions;

    public Set<Auction> getAuctions() {
        return auctions;
    }

    public void setAuctions(Set<Auction> auctions) {
        this.auctions = auctions;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    //esli slomajetsya, to tut
    @OneToMany(mappedBy = "customer", cascade = {
            CascadeType.PERSIST
    })
    private List<Order> ListOfOrders;

    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Order> getListOfOrders() {
        return ListOfOrders;
    }

    public void setListOfOrders(List<Order> listOfOrders) {
        ListOfOrders = listOfOrders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public int hashCode() {
        return Objects.hash(login, password, email, role);
    }

    @Override
    public String toString() {
        return "User{" + "'login" + login + '\'' + "'password:" + password + '\'' + "'email:'"
                + email + '\'' + "'roles:'" + role + '\'' + "'name:'" + name + '\'' + "'surname" + surname + '\'' + "}" + '\'';
    }

    public enum Role {
        USER("user"),
        ADMIN("admin");

        private final String name;


        Role(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }


}

