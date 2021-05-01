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

import br.com.bearblog.noticias.dto.RequisicaoNovaNoticia;
import br.com.bearblog.noticias.model.Noticia;
import br.com.bearblog.noticias.repository.AutorRepository;
import br.com.bearblog.noticias.repository.NoticiaRepository;


@Controller
@RequestMapping("noticia")
public class NoticiaController {
	
		
	@Autowired
	private NoticiaRepository noticiaRepository;
	
	@Autowired
	private AutorRepository autorRepository;

	@GetMapping
	public ModelAndView formulario(RequisicaoNovaNoticia requisicao) {
		return new ModelAndView("noticia/formulario");
	}
	
	@PostMapping
	public ModelAndView nova(@Valid RequisicaoNovaNoticia requisicao, BindingResult result) {		
		
		if(result.hasErrors()) {
			return new ModelAndView("noticia/formulario");
		}
		
		Noticia noticia = requisicao.toNoticia(autorRepository);
		noticiaRepository.save(noticia);	
		return new ModelAndView("/home");
	}
	
	@GetMapping("{id}")
	public String noticia(@PathVariable Long id, Model model, Principal principal) {
		
		Optional<Noticia> noticia = noticiaRepository.findById(id);
		
		if(noticia.isPresent()) {
			model.addAttribute("noticia", noticia.get());		
			return "noticia/noticia";
		}	
	
	return "redirect:/home";
		
	}
	
	
}
