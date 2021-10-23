package br.com.bearblog.postItems.controller;

import br.com.bearblog.postItems.dto.UserDto;
import br.com.bearblog.postItems.model.User;
import br.com.bearblog.postItems.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("register")
public class RegisterController {
	
	@Autowired
	UserService userService;

	@PostMapping
	public ResponseEntity<?> registerNewUser(@Valid UserDto user) {

		Optional<User> existingUser = userService.findByEmail(user.getEmail());

		if(existingUser.isPresent()) {
			// Returns 200 and the existing user's mail. Maybe change to code 409?
			return ResponseEntity.ok().body(existingUser.get().getEmail());
		}
		
		userService.saveNewUser(user);
		
		return ResponseEntity.ok().build();
	}
}
