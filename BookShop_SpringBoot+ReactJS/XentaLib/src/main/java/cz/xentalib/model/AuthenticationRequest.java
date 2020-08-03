package cz.xentalib.model;

/**
 * AuthenticationRequest wich is used for JwtRequestFilter
 */
public class AuthenticationRequest {
    private String username;
    private String password;

    public AuthenticationRequest(String username, String password) {
        this.password = password;
        this.username = username;
    }

    public AuthenticationRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
