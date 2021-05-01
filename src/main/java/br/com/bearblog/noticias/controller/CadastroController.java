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

import br.com.bearblog.noticias.dto.RequisicaoNovoUser;
import br.com.bearblog.noticias.model.Autor;
import br.com.bearblog.noticias.repository.AutorRepository;

@Controller
@RequestMapping("cadastro")
public class CadastroController {
	
	@Autowired
	AutorRepository autorRepository;
	
	@GetMapping
	public ModelAndView cadastro(RequisicaoNovoUser requisicao) {
		return new ModelAndView("user/form");
	}

	@PostMapping
	public ModelAndView novo(@Valid RequisicaoNovoUser requisicao, BindingResult result) {
		if(result.hasErrors()) {
			return new ModelAndView("user/form");
		}
		
		Autor autor = requisicao.toUser(autorRepository);
		
		Optional<Autor> existe = autorRepository.findByEmail(autor.getEmail());
		if(existe.isPresent()) {
			return new ModelAndView("user/form").addObject("objeto", existe.get().getEmail());
		}
		
		autorRepository.save(autor);
		
		return new ModelAndView("login");
	}
}
