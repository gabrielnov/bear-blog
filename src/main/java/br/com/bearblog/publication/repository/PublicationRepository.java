package br.com.bearblog.publication.repository;

import br.com.bearblog.publication.model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long>{
	Optional<Publication> findById(Long id);
	Optional<Publication> findByTitleContaining(String title);
}



