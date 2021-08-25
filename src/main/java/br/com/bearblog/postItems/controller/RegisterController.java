package br.com.bearblog.postItems.controller;

import br.com.bearblog.postItems.dto.UserDto;
import br.com.bearblog.postItems.model.User;
import br.com.bearblog.postItems.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("register")
public class RegisterController {
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping
	public ModelAndView register(UserDto request) {
		return new ModelAndView("user/form");
	}

	@PostMapping
	public ModelAndView registerNewUser(@Valid UserDto request, BindingResult result) {

		if(result.hasErrors()) {
			return new ModelAndView("user/form");
		}
		
		User user = request.toUser(userRepository);
		
		Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

		if(existingUser.isPresent()) {
			return new ModelAndView("user/form").addObject("object", existingUser.get().getEmail());
		}
		
		userRepository.save(user);
		
		return new ModelAndView("login");
	}
}
