package br.com.bearblog.postItems.repository;

import br.com.bearblog.postItems.model.PostItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostItemRepository extends JpaRepository<PostItem, Long>{

	Optional<PostItem> findById(Long id);

}



