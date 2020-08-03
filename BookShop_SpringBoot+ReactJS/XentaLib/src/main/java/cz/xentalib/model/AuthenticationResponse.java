package cz.xentalib.model;

/**
 * AuthenticationResponse wich is used for JwtRequestFilter
 */
public class AuthenticationResponse {
    private final String jwt;
    private final String role;
    private final String name;
    private final String surname;

    public AuthenticationResponse(String jwt, String role, String name, String surname) {
        this.jwt = jwt;
        this.role = role;
        this.name = name;
        this.surname = surname;
    }

    public String getJwt() {
        return jwt;
    }

    public String getRole() {
        return role;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }
}
