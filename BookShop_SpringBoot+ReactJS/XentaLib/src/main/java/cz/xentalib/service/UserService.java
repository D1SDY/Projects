package cz.xentalib.service;

import cz.xentalib.dao.BaseDao;
import cz.xentalib.dao.UserDao;
import cz.xentalib.dto.StoreUserDto;
import cz.xentalib.model.Auction;
import cz.xentalib.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * This object implements User Service
 */
@Service
public class UserService extends BaseService<User> implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private AuctionService auctionService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return new UserDetails(userDao.findByUsername(s));
    }

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    protected BaseDao<User> getPrimaryDao() {
        return userDao;
    }

    public int getBalance(User user) {
        return userDao.findByUsername(user.getLogin()).getBalance();
    }

    /**
     * Return All auctions where user is recorded as participant
     * @param id
     * @return
     */
    public List<Auction> getAllForUser(Integer id){
        List<Auction> temp=auctionService.findAll();
        List<Auction> result=new ArrayList<>();
        User user=find(id);
        for(int i=0;i<temp.size();i++){
            if(temp.get(i).getParticipants().contains(user)){
                result.add(temp.get(i));
            }
        }
        return result;
    }

    /**
     * Store new user into DB
     * @param userDto
     */
    @Transactional
    public void store(StoreUserDto userDto) {
        User user = new User();
        user.setLogin(userDto.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setEmail(userDto.getEmail());
        user.setBalance(0);
        user.setRole(User.Role.USER);
        persist(user);
    }

    /**
     * Update user
     * @param userDto
     * @param user
     */
    @Transactional
    public void update(StoreUserDto userDto,User user){
        user.setLogin(userDto.getUsername());
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setEmail(userDto.getEmail());
        update(user);
    }

}
