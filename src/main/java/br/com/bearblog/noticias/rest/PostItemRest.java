package br.com.bearblog.noticias.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bearblog.noticias.model.PostItem;
import br.com.bearblog.noticias.repository.PostItemRepository;

@RestController
@RequestMapping("/api/noticias")
public class PostItemRest {

	@Autowired
	private PostItemRepository noticiaRepository;
	
	// m�todo da interface repository
	@GetMapping("publicadas")
	public List<PostItem> getPostItem(){
		return noticiaRepository.findAll();
	}
}
