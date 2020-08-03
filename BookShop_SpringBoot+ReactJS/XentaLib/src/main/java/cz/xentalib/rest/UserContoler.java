package cz.xentalib.rest;

import cz.xentalib.dto.StoreUserDto;
import cz.xentalib.model.Auction;
import cz.xentalib.model.User;
import cz.xentalib.service.AuctionService;
import cz.xentalib.service.UserService;
import cz.xentalib.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Object wich represents  User Controller
 */
@RestController
@RequestMapping("/users")
public class UserContoler {
    @Autowired
    private final UserService userService;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UserContoler(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Method wich represents endpoint /current and return current user object
     * @return
     */
    @GetMapping("/current")
    public User getCurrentUser(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization").split(" ")[1];
        return userService.findByUsername(new JwtUtil().extractUsername(token));
    }

    /**
     * Method wich represents endpoint /all and returns all users for admin only
     * @return
     */
    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/all")
    public List<User> getAllUsers(){
       return userService.findAll();
    }

    /**
     * Method wich allows to update user info
     * @param userDto
     * @param user
     * @return
     */
    @PostMapping("/update")
    public String update(@RequestBody StoreUserDto userDto,User user){
        userService.update(userDto,getCurrentUser());
        return "ok";
    }

    /**
     * Method wich allows to find user by his id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public User findById(@PathVariable Integer id){
        return userService.find(id);
    }

    /**
     * Methid wich returns list of auctions where user is added like participant
     * @param id
     * @return
     */
    @GetMapping("/all/auctions/{id}")
    public List<Auction> getAllForUser(@PathVariable Integer id){
        return userService.getAllForUser(id);
    }
}
