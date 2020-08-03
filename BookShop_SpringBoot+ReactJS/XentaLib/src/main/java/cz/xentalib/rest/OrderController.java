package cz.xentalib.rest;

import cz.xentalib.model.Order;
import cz.xentalib.model.User;
import cz.xentalib.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Object wich represents Order Contoller
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserContoler userContoler;

    /**
     * Method wich represents endpoint /{bookId} and allow to add book with given
     * id:bookId to DB
     * @param bookId
     * @return
     */
    @PostMapping("/{bookId}")
    public String store(@PathVariable Integer bookId){
        User user=userContoler.getCurrentUser();
        orderService.store(bookId,user);
        return "ok";
    }

    /**
     * Method wich represents endpoint /current and return all orders for current user
     * @return
     */
    @GetMapping("/current")
    public List<Order> getAllForCurrent(){
        User user=userContoler.getCurrentUser();
        return orderService.getAllForUser(user);

    }

}
