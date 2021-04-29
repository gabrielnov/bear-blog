package br.com.bearblog.noticias.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.bearblog.noticias.model.Noticia;
import br.com.bearblog.noticias.repository.NoticiaRepository;

@Controller
@RequestMapping
public class HomeController {
	
	@Autowired
	private NoticiaRepository noticiaRepository;

	@GetMapping("/home")
	@Cacheable(value="listaDeNoticias")
	public String home(Model model) {
		Sort sort = Sort.by("data").descending();
	//	PageRequest paginacao = PageRequest.of(0, 10, sort);
		List<Noticia> noticias = noticiaRepository.findAll(sort);
		model.addAttribute("noticias", noticias);
		return "home";
	}
	
	@GetMapping("/")
	public String home() {
		return "redirect:/home";
	}

}
