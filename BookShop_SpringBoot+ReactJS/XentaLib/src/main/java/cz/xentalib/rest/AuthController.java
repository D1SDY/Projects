package cz.xentalib.rest;

import cz.xentalib.dto.StoreUserDto;
import cz.xentalib.model.AuthenticationRequest;
import cz.xentalib.model.AuthenticationResponse;
import cz.xentalib.service.UserService;
import cz.xentalib.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Contoller wich represents login and registration logic
 */
@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @RequestMapping(value = "/authentication", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        final String role = userDetails.getAuthorities().toString();

        final String name = userService.findByUsername(userDetails.getUsername()).getName();

        final String surname = userService.findByUsername(userDetails.getUsername()).getSurname();

        return ResponseEntity.ok(new AuthenticationResponse(jwt, role, name, surname));
    }

    @RequestMapping("/register")
    public String register(@RequestBody StoreUserDto userDto) {
        userService.store(userDto);
        return "ok";
    }
}
