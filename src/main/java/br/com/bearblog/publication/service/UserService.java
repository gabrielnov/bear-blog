package br.com.bearblog.publication.service;

import br.com.bearblog.publication.dto.UserDto;
import br.com.bearblog.publication.model.User;
import br.com.bearblog.publication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User saveUser(UserDto userDto){

        User user = userDto.userFactory();
        userRepository.save(user);
        return user;
    }
}
