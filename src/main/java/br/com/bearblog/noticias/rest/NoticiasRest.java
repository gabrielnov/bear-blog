package br.com.bearblog.noticias.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bearblog.noticias.model.Noticia;
import br.com.bearblog.noticias.repository.NoticiaRepository;

@RestController
@RequestMapping("/api/noticias")
public class NoticiasRest {

	@Autowired
	private NoticiaRepository noticiaRepository;
	
	// método da interface repository
	@GetMapping("publicadas")
	public List<Noticia> getNoticias(){
		return noticiaRepository.findAll();
	}
}
