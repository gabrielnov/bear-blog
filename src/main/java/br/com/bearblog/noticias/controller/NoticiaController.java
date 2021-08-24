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
import org.springframework.web.servlet.ModelAndView;

import br.com.bearblog.noticias.dto.PostItemDto;
import br.com.bearblog.noticias.model.PostItem;
import br.com.bearblog.noticias.repository.UserRepository;
import br.com.bearblog.noticias.repository.PostItemRepository;


@Controller
public class NoticiaController {
	
		
	@Autowired
	private PostItemRepository postItemRepository;
	
	@Autowired
	private UserRepository userRepository;

	
	@GetMapping("noticia")
	public ModelAndView formulario(PostItemDto requisicao) {
		return new ModelAndView("noticia/formulario");
	}
	
	@PostMapping("noticia")
	public ModelAndView nova(@Valid PostItemDto requisicao, Model model, BindingResult result) {
		
		if(result.hasErrors()) {
			return new ModelAndView("noticia/formulario");
		}
		
		PostItem postItem = requisicao.postItemConverter(userRepository);
		postItemRepository.save(postItem);
		
		model.addAttribute("id", postItem.getId());
		return new ModelAndView("redirect:/home");
	}
	
	@GetMapping("noticia/{id}")
	public String noticia(@PathVariable Long id, Model model, Principal principal) {
		
		Optional<PostItem> postItem = postItemRepository.findById(id);
		
		if(postItem.isPresent()) {
			model.addAttribute("noticia", postItem.get());
			return "noticia/noticia";
		}	
	
	return "redirect:/home";
		
	}
	
	
}
