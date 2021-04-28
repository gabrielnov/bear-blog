package br.com.bearblog.noticias.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bearblog.noticias.model.Noticia;


//implementacao da interface repository
public interface NoticiaRepository extends JpaRepository<Noticia, Long>{
	


}
