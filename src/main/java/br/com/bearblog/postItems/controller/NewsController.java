package br.com.bearblog.postItems.controller;

import br.com.bearblog.postItems.dto.NewsDto;
import br.com.bearblog.postItems.model.News;
import br.com.bearblog.postItems.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class NewsController {

	@Autowired
	private NewsService newsService;

	@GetMapping("/api/news/")
	@Cacheable(value="newsList")
	public ResponseEntity<List<News>> getAll() {
		List<News> news = newsService.findAll();
		return ResponseEntity.ok().body(news);
	}

	@GetMapping("api/news/{id}")
	public ResponseEntity<News> getNews(@PathVariable Long id) {
		Optional<News> newsModel = newsService.findById(id);
		if(newsModel.isPresent()) {
			return ResponseEntity.ok().body(newsModel.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping("/api/news")
	public ResponseEntity<?> publishNews(@RequestBody NewsDto news) {
		newsService.saveNews(news);
		return ResponseEntity.noContent().build();
	}

	@ExceptionHandler(ClassCastException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> handleNoSuchElementFoundException(ClassCastException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("An error ocurred during your request. Please contract an administrator.");
	}
}
