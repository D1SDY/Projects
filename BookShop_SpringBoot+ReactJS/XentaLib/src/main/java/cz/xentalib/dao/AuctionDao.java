package cz.xentalib.dao;

import cz.xentalib.model.Auction;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * Class wich represents AuctionDao where we implemented all queries
 */
@Repository
public class AuctionDao extends BaseDao<Auction> {

    protected AuctionDao() {
        super(Auction.class);
    }

    /**
     * Query for find auction by name
     * @param name
     * @return
     */
    public Auction findByName(String name){
        try{
            return em.createNamedQuery("Auction.findByName",Auction.class)
                    .setParameter("name",name).getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    /**
     * Query for find auction by id book id
     * @param id
     * @return
     */
    public Auction findByBookId(Integer id){
        try{
            return em.createNamedQuery("Auction.findByBookId",Auction.class)
                    .setParameter("id",id).getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    /**
     * Query for find auction by id of organizer
     * @param id
     * @return
     */
    public Auction findByOrganizer(Integer id){
        try{
            return em.createNamedQuery("Auction.findByOrganizer",Auction.class)
                    .setParameter("id",id).getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

}
