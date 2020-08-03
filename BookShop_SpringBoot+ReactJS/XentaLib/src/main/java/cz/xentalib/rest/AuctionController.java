package cz.xentalib.rest;

import cz.xentalib.dto.StoreAuctionDto;
import cz.xentalib.model.Auction;
import cz.xentalib.model.Order;
import cz.xentalib.model.User;
import cz.xentalib.service.AuctionService;
import cz.xentalib.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Object wich represent Auction Controller
 */
@RestController
@RequestMapping("/auctions")
public class AuctionController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "AuctionReport";

    @Autowired
    private final AuctionService auctionService;
    @Autowired
    private UserContoler userContoler;

    private final KafkaService ks;

    public AuctionController(AuctionService auctionService,KafkaService ks) {
        this.auctionService = auctionService;
        this.ks = ks;
    }

    /**
     * Method wich add new auction to DB, and report to reports.txt using kafka
     * @param auctionDto
     * @return
     */
    @PostMapping
    public String storeAuction(@RequestBody StoreAuctionDto auctionDto) {
        auctionService.store(auctionDto, userContoler.getCurrentUser());
        String message = "Creator \"" + userContoler.getCurrentUser() + "[ID:" + userContoler.getCurrentUser().getId() +
                "]\" created auction with ammount of buyers " + auctionDto.getAmmoutOfParticipants() + " for book - " +
                auctionDto.getBook();
        kafkaTemplate.send(TOPIC, message);
        return "ok";
    }

    /**
     * Method wich represents end point /{name} wich allows to get auction by name
     * @param name
     * @return
     */
    @GetMapping("/{name}")
    public Auction getAuctionByName(@PathVariable String name) {
        return auctionService.findByName(name);
    }

    /**
     * Method wich represents end point /bood-id-{id} wich allows to get auction by book id
     * @param id
     * @return
     */
    @GetMapping("/book-id-{id}")
    public Auction getAuctionByBookId(@PathVariable Integer id) {
        return auctionService.findByBookId(id);
    }

    /**
     * Method wich represents end point /organizer-id-{id} wich allows to get auction by organizre id
     * @param id
     * @return
     */
    @GetMapping("/organizer-id-{id}")
    public Auction getAuctionByOrganizerId(@PathVariable Integer id) {
        return auctionService.findByOrganizerId(id);
    }

    /**
     * Method wich represents end point /all wich allows to get all auctions (only for admin)
     * @return
     */
    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/all")
    public List<Auction> getAllAuctions() {
        return auctionService.findAll();
    }

    /**
     * Method wich represents end point /finish{id} wich returns end date for auction with given id
     * @param id
     * @return
     */
    @GetMapping("/finish{id}")
    public Date getAuctionDeadline(@PathVariable Integer id){

        return auctionService.find(id).getFinish();
    }

    /**
     * Method wich represents end point /add/{id}/{name} wich add paricipant with id:id to auction with given name:name
     * @param id
     * @param name
     * @return
     */
    @PostMapping("/add/{id}/{name}")
    public String addParticipant(@PathVariable Integer id,@PathVariable String name){
        auctionService.addParticipant(id,name);
        return "ok";
    }

    /**
     * Method wich represents end point /participants/{name} wich return list of all participant of auction with
     * given name:name
     * @param name
     * @return
     */
    @GetMapping("/participants/{name}")
    public Set<User> getAllParticipants(@PathVariable String name){
        return auctionService.getAllParticipants(name);
    }

    /**
     * Method wich represents end point for /{id}/{name}/{bet} with allows to make bet:bet user wich given id:id
     * to auction with given name:name
     * @param name
     * @param id
     * @param bet
     * @return
     */
    @PostMapping("/{id}/{name}/{bet}")
    public String makeBet(@PathVariable String name,@PathVariable Integer id,@PathVariable Integer bet){
        auctionService.makeBet(name,id,bet);
        return "ok";
    }


}
