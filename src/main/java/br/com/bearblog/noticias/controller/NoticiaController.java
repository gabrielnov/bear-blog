package br.com.bearblog.noticias.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	
}
