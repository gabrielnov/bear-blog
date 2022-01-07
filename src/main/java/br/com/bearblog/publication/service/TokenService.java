package br.com.bearblog.publication.service;

import br.com.bearblog.publication.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${forum.jwt.expiration}")
    private String expiration;

    @Value("${forum.jwt.secret}")
    private String secret;

    public String generateToken (Authentication authentication){

        User loggedUser = (User) authentication.getPrincipal();

        Date expirationDate = new Date(new Date().getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("BearBlog")
                .setSubject(loggedUser.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
