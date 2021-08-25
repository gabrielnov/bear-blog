package br.com.bearblog.postItems.controller;

import br.com.bearblog.postItems.dto.PostItemDto;
import br.com.bearblog.postItems.model.PostItem;
import br.com.bearblog.postItems.repository.PostItemRepository;
import br.com.bearblog.postItems.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;


@Controller
public class PostItemController {
		
	@Autowired
	private PostItemRepository postItemRepository;
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping("postItem")
	public ModelAndView form(PostItemDto request) {
		return new ModelAndView("postItem/postItemForm");
	}
	
	@PostMapping("postItem")
	public ModelAndView newPostItem(@Valid PostItemDto request, Model model, BindingResult result) {
		
		if(result.hasErrors()) {
			return new ModelAndView("postItem/postItemForm");
		}
		
		PostItem postItem = request.postItemConverter(userRepository);
		postItemRepository.save(postItem);
		
		model.addAttribute("id", postItem.getId());
		return new ModelAndView("redirect:/home");
	}
	
	@GetMapping("postItem/{id}")
	public String postItem(@PathVariable Long id, Model model, Principal principal) {
		
		Optional<PostItem> postItem = postItemRepository.findById(id);
		
		if(postItem.isPresent()) {
			model.addAttribute("postItem", postItem.get());
			return "postItem/postItem";
		}	
	
	return "redirect:/home";
		
	}
	
	
}
