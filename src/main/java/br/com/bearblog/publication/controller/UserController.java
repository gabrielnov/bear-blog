package br.com.bearblog.publication.controller;

import br.com.bearblog.publication.dto.TokenDto;
import br.com.bearblog.publication.security.LoginDto;
import br.com.bearblog.publication.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
public class UserController {

    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

    @PostMapping("/auth")
    public ResponseEntity<?> auth(@RequestBody @Valid LoginDto loginDto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
        Authentication authentication = authManager.authenticate(usernamePasswordAuthenticationToken);
        String token = tokenService.generateToken(authentication);

        return ResponseEntity.ok(TokenDto.builder().type("Bearer").token(token).build());
    }

    public void setAuthManager(AuthenticationManager authManager) {
        this.authManager = authManager;
    }
}
