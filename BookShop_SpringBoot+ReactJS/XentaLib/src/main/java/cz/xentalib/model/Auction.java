package cz.xentalib.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Object wich represents Auction Table
 */
@Entity
@Table(name = "auctions")
@NamedQueries({
        @NamedQuery(name = "Auction.findByName",
                query = "select a from Auction a where a.name=:name"),
        @NamedQuery(name = "Auction.findByBookId",
                query = "select a from Auction a where a.book.id=:id"),
        @NamedQuery(name = "Auction.findByOrganizer",
                query = "select  a from Auction a where a.organizer.id=:id"),
})
public class Auction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "organizer_id")
    private User organizer;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "book")
    private Book book;

    @Column
    private String name;

    @Column
    private int ammoutOfParticipants;

    @Column
    private Date date;

    @Column
    private Date finish;

    @Column
    private boolean isFinished;

    @ManyToMany
    private Set<User> participants;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "lastbet_id")
    private User lastBet;

    public User getLastBet() {
        return lastBet;
    }

    public void setLastBet(User lastBet) {
        this.lastBet = lastBet;
    }

    public Set<User> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<User> participants) {
        this.participants = participants;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public Date getFinish() {
        return finish;
    }

    public void setFinish(Date end) {
        this.finish = end;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getOrganizer() {
        return organizer;
    }

    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmmoutOfParticipants() {
        return ammoutOfParticipants;
    }

    public void setAmmoutOfParticipants(int ammoutOfParticipants) {
        this.ammoutOfParticipants = ammoutOfParticipants;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
