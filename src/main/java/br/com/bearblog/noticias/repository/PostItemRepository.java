package br.com.bearblog.noticias.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bearblog.noticias.model.PostItem;


//implementacao da interface repository
public interface PostItemRepository extends JpaRepository<PostItem, Long>{

	Optional<PostItem> findById(Long id);

	
}



