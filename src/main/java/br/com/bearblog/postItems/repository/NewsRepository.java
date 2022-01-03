package br.com.bearblog.postItems.repository;

import br.com.bearblog.postItems.model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<Publication, Long>{
	Optional<Publication> findById(Long id);
	Optional<Publication> findByTitleContaining(String title);
}



