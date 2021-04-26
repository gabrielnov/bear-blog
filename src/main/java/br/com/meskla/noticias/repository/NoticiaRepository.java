package br.com.meskla.noticias.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.meskla.noticias.model.Noticia;


//implementacao da interface repository
public interface NoticiaRepository extends JpaRepository<Noticia, Long>{
	


}
