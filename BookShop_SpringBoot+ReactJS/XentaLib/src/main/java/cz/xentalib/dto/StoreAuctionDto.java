package cz.xentalib.dto;

import cz.xentalib.model.Book;

import java.util.Date;

/**
 * Dto wich is used for creation of new auction,
 * we are getting new StoreAuctionDto object from react application
 */
public class StoreAuctionDto {
    private int ammoutOfParticipants;
    private Date start;
    private Date end;
    private Book book;
    private boolean isFinished;

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    private String name;

    public void setStart(Date start) {
        this.start = start;
    }

    public void setEnd(Date end) {
        this.end = end;
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
}
