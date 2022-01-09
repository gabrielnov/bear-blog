package br.com.bearblog.publication.controller;

import br.com.bearblog.publication.dto.TokenDto;
import br.com.bearblog.publication.dto.LoginDto;
import br.com.bearblog.publication.dto.UserDto;
import br.com.bearblog.publication.model.User;
import br.com.bearblog.publication.service.TokenService;
import br.com.bearblog.publication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
public class UserController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

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

    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserDto userDto){
        User user = userService.saveUser(userDto);
        return ResponseEntity.ok().body(user);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException exception) {
        return ResponseEntity.status(HttpStatus.OK).body("Invalid credentials.");
    }

}
