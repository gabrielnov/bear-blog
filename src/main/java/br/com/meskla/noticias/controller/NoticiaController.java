package br.com.meskla.noticias.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.meskla.noticias.dto.RequisicaoNovaNoticia;
import br.com.meskla.noticias.repository.NoticiaRepository;


@Controller
@RequestMapping("/noticia")
public class NoticiaController {
	
	@Autowired
	private NoticiaRepository noticiaRepository;

	@GetMapping("/nova") 
	public String formulario(RequisicaoNovaNoticia requisicao) {
		return "noticia/formulario";
	}
	
}
