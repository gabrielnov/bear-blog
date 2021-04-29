package br.com.bearblog.noticias.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.bearblog.noticias.dto.RequisicaoNovaNoticia;
import br.com.bearblog.noticias.model.Noticia;
import br.com.bearblog.noticias.repository.NoticiaRepository;


@Controller
@RequestMapping("noticia")
public class NoticiaController {
	
	@Autowired
	private NoticiaRepository noticiaRepository;

	@GetMapping("formulario")
	public String formulario(RequisicaoNovaNoticia requisicao) {
		return "noticia/formulario";
	}
	
	@PostMapping("nova") 
	public String nova(@Valid RequisicaoNovaNoticia requisicao) {
		Noticia noticia = requisicao.toNoticia();
		noticiaRepository.save(noticia);
	
		return "redirect:/home";
	}
	
	
}
