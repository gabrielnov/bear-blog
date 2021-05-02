package br.com.bearblog.noticias.controller;

import java.security.Principal;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.bearblog.noticias.dto.NoticiaDto;
import br.com.bearblog.noticias.model.Noticia;
import br.com.bearblog.noticias.repository.UserRepository;
import br.com.bearblog.noticias.repository.NoticiaRepository;


@Controller
public class NoticiaController {
	
		
	@Autowired
	private NoticiaRepository noticiaRepository;
	
	@Autowired
	private UserRepository userRepository;

	
	@GetMapping("noticia")
	public ModelAndView formulario(NoticiaDto requisicao) {
		return new ModelAndView("noticia/formulario");
	}
	
	@PostMapping("noticia")
	public ModelAndView nova(@Valid NoticiaDto requisicao, Model model, BindingResult result) {		
		
		if(result.hasErrors()) {
			return new ModelAndView("noticia/formulario");
		}
		
		Noticia noticia = requisicao.toNoticia(userRepository);
		noticiaRepository.save(noticia);	
		
		model.addAttribute("id", noticia.getId());		
		return new ModelAndView("redirect:/home");
	}
	
	@GetMapping("noticia/{id}")
	public String noticia(@PathVariable Long id, Model model, Principal principal) {
		
		Optional<Noticia> noticia = noticiaRepository.findById(id);
		
		if(noticia.isPresent()) {
			model.addAttribute("noticia", noticia.get());		
			return "noticia/noticia";
		}	
	
	return "redirect:/home";
		
	}
	
	
}
