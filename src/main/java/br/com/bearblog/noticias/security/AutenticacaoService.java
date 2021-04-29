package br.com.bearblog.noticias.security;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.bearblog.noticias.model.Autor;
import br.com.bearblog.noticias.repository.AutorRepository;


@Service
public class AutenticacaoService  implements UserDetailsService {
	
	@Autowired
	AutorRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Autor> autor = repository.findByEmail(username);
		
		if(autor.isPresent()) {
			return autor.get();
		}
		throw new UsernameNotFoundException("Dados inválidos!");
	}

}
