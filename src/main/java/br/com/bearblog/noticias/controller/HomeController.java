package br.com.bearblog.noticias.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.bearblog.noticias.model.PostItem;
import br.com.bearblog.noticias.repository.PostItemRepository;

@Controller
@RequestMapping("/home") // preciso adicionar o "/"
public class HomeController {
	
	@Autowired
	private PostItemRepository noticiaRepository;

	@GetMapping()
	@Cacheable(value="listaDeNoticias")
	public ModelAndView home(Model model, Principal principal) {
		Sort sort = Sort.by("data").descending();
	//	PageRequest paginacao = PageRequest.of(0, 10, sort);
		List<PostItem> noticias = noticiaRepository.findAll(sort);
		model.addAttribute("noticias", noticias);
		return new ModelAndView("home");
	}
	
}
