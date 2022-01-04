package br.com.bearblog.publication.controller;

import br.com.bearblog.publication.dto.PublicationDto;
import br.com.bearblog.publication.model.Publication;
import br.com.bearblog.publication.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class NewsController {

	@Autowired
	private PublicationService publicationService;

	@GetMapping("/api/publication/")
	public ResponseEntity<List<Publication>> getAll() {
		List<Publication> news = publicationService.findAll();
		return ResponseEntity.ok().body(news);
	}

	@GetMapping("api/publication/{id}")
	public ResponseEntity<Publication> getPublication(@PathVariable Long id) {
		Optional<Publication> newsModel = publicationService.findById(id);
		if(newsModel.isPresent()) {
			return ResponseEntity.ok().body(newsModel.get());
		}
		return ResponseEntity.notFound().build();
	}

	// TODO: return URI
	@PostMapping("/api/publication")
	public ResponseEntity<Publication> publish(@RequestBody PublicationDto news) {
		Publication publication = publicationService.savePublication(news);
		return ResponseEntity.ok().body(publication);
	}

	@ExceptionHandler(ClassCastException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> handleNoSuchElementFoundException(ClassCastException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("An error ocurred during your request. Please contract an administrator.");
	}
}
