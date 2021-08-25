package br.com.bearblog.postItems.controller;

import br.com.bearblog.postItems.model.PostItem;
import br.com.bearblog.postItems.repository.PostItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private PostItemRepository postItemRepository;

	@GetMapping()
	@Cacheable(value="postItemList")
	public ModelAndView home(Model model, Principal principal) {
		Sort sort = Sort.by("date").descending();
		List<PostItem> postItems = postItemRepository.findAll(sort);
		model.addAttribute("postItems", postItems);
		return new ModelAndView("home");
	}
	
}
