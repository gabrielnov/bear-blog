package br.com.bearblog.publication.controller;

import br.com.bearblog.publication.dto.TokenDto;
import br.com.bearblog.publication.security.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.token.TokenService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
public class UserController {

    private AuthenticationManager authManager;
    private TokenService tokenService;

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

    @RequestMapping("/auth")
    public ResponseEntity<?> auth(@RequestBody @Valid LoginForm form) {
        UsernamePasswordAuthenticationToken loginData = form.converter();

        try {
            Authentication authentication = authManager.authenticate(loginData);
            String token = tokenService.generateToken(authentication);
            return ResponseEntity.ok(new TokenDto(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    public void setAuthManager(AuthenticationManager authManager) {
        this.authManager = authManager;
    }

    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }
}
