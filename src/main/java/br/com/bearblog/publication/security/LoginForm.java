package br.com.bearblog.publication.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {

    private String username;
    private String password;

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

    public UsernamePasswordAuthenticationToken converter(){
        return new UsernamePasswordAuthenticationToken(username, password);
    }
}
