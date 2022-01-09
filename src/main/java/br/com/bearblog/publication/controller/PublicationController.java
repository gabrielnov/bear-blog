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
public class PublicationController {

	@Autowired
	private PublicationService publicationService;

	@GetMapping("/api/publication/")
	public ResponseEntity<List<Publication>> getAll() {
		List<Publication> publications = publicationService.findAll();
		return ResponseEntity.ok().body(publications);
	}

	@GetMapping("api/publication/{id}")
	public ResponseEntity<Publication> getPublication(@PathVariable Long id) {
		Optional<Publication> publication = publicationService.findById(id);
		if(publication.isPresent()) {
			return ResponseEntity.ok().body(publication.get());
		}
		return ResponseEntity.notFound().build();
	}

	// TODO: change to created
	@PostMapping("/api/publication")
	public ResponseEntity<Publication> publish(@RequestBody PublicationDto news) {
		Publication publication = publicationService.savePublication(news);
		return ResponseEntity.ok().body(publication);
	}

	@ExceptionHandler(ClassCastException.class)
	public ResponseEntity<String> handleNoSuchElementFoundException(ClassCastException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error ocurred during your request. Please contract an administrator.");
	}
}
