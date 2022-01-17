package br.com.bearblog.publication.repository;

import br.com.bearblog.publication.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUsernameOrEmail(String username, String email);
    Boolean existsByEmail(String email);
}

