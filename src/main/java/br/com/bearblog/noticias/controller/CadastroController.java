package br.com.bearblog.noticias.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.bearblog.noticias.dto.UserDto;
import br.com.bearblog.noticias.model.User;
import br.com.bearblog.noticias.repository.UserRepository;

@Controller
@RequestMapping("cadastro")
public class CadastroController {
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping
	public ModelAndView cadastro(UserDto requisicao) {
		return new ModelAndView("user/form");
	}

	@PostMapping
	public ModelAndView novo(@Valid UserDto requisicao, BindingResult result) {
		if(result.hasErrors()) {
			return new ModelAndView("user/form");
		}
		
		User user = requisicao.toUser(userRepository);
		
		Optional<User> existe = userRepository.findByEmail(user.getEmail());
		if(existe.isPresent()) {
			return new ModelAndView("user/form").addObject("objeto", existe.get().getEmail());
		}
		
		userRepository.save(user);
		
		return new ModelAndView("login");
	}
}
