package br.com.bearblog.noticias.security;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.bearblog.noticias.model.User;
import br.com.bearblog.noticias.repository.UserRepository;


@Service
public class AutenticacaoService  implements UserDetailsService {
	
	@Autowired
	UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = repository.findByEmail(username);
		
		if(user.isPresent()) {
			return user.get();
		}
		throw new UsernameNotFoundException("Dados inválidos!");
	}

}
