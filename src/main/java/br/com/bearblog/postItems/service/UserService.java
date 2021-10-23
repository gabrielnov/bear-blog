package br.com.bearblog.postItems.service;

import br.com.bearblog.postItems.dto.UserDto;
import br.com.bearblog.postItems.model.User;
import br.com.bearblog.postItems.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void saveNewUser(UserDto userDto) {
        userRepository.save(userDto.toUser());
    }
}
