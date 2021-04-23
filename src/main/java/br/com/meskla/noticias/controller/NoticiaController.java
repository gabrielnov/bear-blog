package br.com.meskla.noticias.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.meskla.noticias.dto.RequisicaoNovaNoticia;


@Controller
@RequestMapping("noticia")
public class NoticiaController {

	@GetMapping("formulario") 
	public String formulario(RequisicaoNovaNoticia requisicao) {
		return "noticia/formulario";
	}
	
}
