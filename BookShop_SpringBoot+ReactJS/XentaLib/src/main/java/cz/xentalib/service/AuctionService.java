package cz.xentalib.service;

import cz.xentalib.dao.AuctionDao;
import cz.xentalib.dao.BaseDao;
import cz.xentalib.dto.StoreAuctionDto;
import cz.xentalib.model.Auction;
import cz.xentalib.model.Book;
import cz.xentalib.model.Order;
import cz.xentalib.model.User;
import cz.xentalib.rest.UserContoler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

/**
 * Object wich implemetns Auction Service
 */
@Service
public class AuctionService extends BaseService<Auction> {
    @Autowired
    private AuctionDao auctionDao;
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;

    @Override
    protected BaseDao<Auction> getPrimaryDao() {
        return auctionDao;
    }

    public Auction findByOrganizerId(Integer id) {
        return auctionDao.findByOrganizer(id);
    }

    public Auction findByBookId(Integer id) {
        return auctionDao.findByBookId(id);
    }

    @Cacheable(value = "auctionCache")
    public Auction findByName(String name) {
        return auctionDao.findByName(name);
    }

    /**
     * Store new auction in DB
     * @param auctionDto
     * @param user
     */
    @Transactional
    public void store(StoreAuctionDto auctionDto, User user) {
        Auction auction = new Auction();
        auction.setName(auctionDto.getName());
        auction.setAmmoutOfParticipants(auctionDto.getAmmoutOfParticipants());
        auction.setDate(auctionDto.getStart());
        auction.setFinish(auctionDto.getEnd());
        auction.setBook(auctionDto.getBook());
        auction.setOrganizer(user);
        auction.setFinished(auctionDto.isFinished());
        persist(auction);
    }

    /**
     * Add participant into list of Participants in Auction
     * @param id
     * @param name
     */
    @Transactional
    public void addParticipant(Integer id, String name){
        Auction auction=findByName(name);
        User user=userService.find(id);
        auction.getParticipants().add(user);
        update(auction);
    }

    /**
     * Return all participants for given auction
     * @param name
     * @return
     */
    @Transactional
    public Set<User> getAllParticipants(String name){
        Auction auction=findByName(name);
        return auction.getParticipants();
    }

    /**
     * Allows user to make his bet
     * @param name
     * @param id
     * @param bet
     */
    @Transactional
    public void makeBet(String name,Integer id,Integer bet){
        Auction auction=findByName(name);
        User user=userService.find(id);
        Book book=auction.getBook();
        auction.setLastBet(user);
        book.setPrice(book.getPrice()+bet);
        update(auction);
        bookService.update(book);
    }

}
