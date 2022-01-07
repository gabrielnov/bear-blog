package br.com.bearblog.publication.service;

import br.com.bearblog.publication.model.User;
import br.com.bearblog.publication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optional = repository.findByEmail(username);

        if(optional.isPresent()){
            return optional.get();
        }

        throw new UsernameNotFoundException("User not found!");
    }
}
