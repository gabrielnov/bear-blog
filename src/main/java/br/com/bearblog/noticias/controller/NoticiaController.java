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
	public String formulario(RequisicaoNovaNoticia requisicao) {
		return "noticia/formulario";
	}
	
	@PostMapping
	public String nova(@Valid RequisicaoNovaNoticia requisicao, BindingResult result) {		
		if(result.hasErrors()) {
			return "noticia/formulario";
		}
		
		Noticia noticia = requisicao.toNoticia(autorRepository);
		noticiaRepository.save(noticia);	
		return "redirect:/home";
	}
	
	
}
