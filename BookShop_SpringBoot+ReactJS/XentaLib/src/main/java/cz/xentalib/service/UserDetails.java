package cz.xentalib.service;


import cz.xentalib.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;


public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

    private User user;

    private final Set<GrantedAuthority> authorities;


    public UserDetails(User user) {
        this.user = user;
        this.authorities = new HashSet<>();
        addUserRole();
    }


    public UserDetails(User user, Collection<GrantedAuthority> authorities) {
        Objects.requireNonNull(user);
        Objects.requireNonNull(authorities);
        this.user = user;
        this.authorities = new HashSet<>();
        addUserRole();
        this.authorities.addAll(authorities);
    }


    public void addUserRole() {
        authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println("-----------------------" + Arrays.toString(authorities.toArray()));
        return Collections.unmodifiableCollection(authorities);
    }


    @Override
    public String getPassword() {
        return user.getPassword();
    }


    @Override
    public String getUsername() {
        return user.getLogin();
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @Override
    public boolean isEnabled() {
        return true;
    }
}
