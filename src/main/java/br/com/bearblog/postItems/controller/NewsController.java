package br.com.bearblog.postItems.controller;

import br.com.bearblog.postItems.dto.NewsDto;
import br.com.bearblog.postItems.model.News;
import br.com.bearblog.postItems.service.NewsService;
import br.com.bearblog.postItems.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Controller
public class NewsController {

	@Autowired
	private NewsService newsService;

	@Autowired
	private UserService userService;

	@GetMapping("/api/news/list")
	@Cacheable(value="newsList")
	public ResponseEntity<List<News>> getAll() {

		Sort sort = Sort.by("date").descending();

		List<News> news = newsService.findAll();
		return ResponseEntity.ok().body(news);
	}

	@PostMapping("/api/news")
	public ResponseEntity<?> publishNews(@Valid NewsDto news) {

		newsService.saveNews(news);

		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("api/news")
	public ResponseEntity<News> getNews(@RequestParam Long id) {
		
		Optional<News> newsModel = newsService.findById(id);

		if(newsModel.isPresent()) {
			return ResponseEntity.ok().body(newsModel.get());
		}
	
		return ResponseEntity.notFound().build();
		
	}
	
	
}
