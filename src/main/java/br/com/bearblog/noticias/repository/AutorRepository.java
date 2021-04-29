package br.com.bearblog.noticias.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bearblog.noticias.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {
	
	Optional<Autor> findByEmail(String email);
}
