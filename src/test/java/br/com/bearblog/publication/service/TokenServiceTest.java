package br.com.bearblog.publication.service;

import br.com.bearblog.publication.security.LoginDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class TokenServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TokenService tokenService;

    private AuthenticationManager authManager;

    @Test
    void shouldReturnValidToken() throws  Exception{
        LoginDto loginDto = new LoginDto("username","password");

        UsernamePasswordAuthenticationToken loginData = loginDto.converter();
        Authentication authentication = authManager.authenticate(loginData);
        tokenService.generateToken(authentication);

    }

    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }
}
