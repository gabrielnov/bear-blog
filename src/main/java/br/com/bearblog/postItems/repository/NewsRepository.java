package br.com.bearblog.postItems.repository;

import br.com.bearblog.postItems.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Long>{
	Optional<News> findById(Long id);
}



