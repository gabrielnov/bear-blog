package br.com.bearblog.publication.service;

import br.com.bearblog.publication.dto.PublicationDto;
import br.com.bearblog.publication.model.Publication;
import br.com.bearblog.publication.repository.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicationService {

    @Autowired
    PublicationRepository publicationRepository;

    public List<Publication> findAll() {
        return publicationRepository.findAll();
    }

    public Optional<Publication> findById(Long id) {
        return publicationRepository.findById(id);
    }

    public Publication savePublication(PublicationDto publicationDto) {
        Publication publication = publicationDto.pulibcationFactory();
        Publication saved = publicationRepository.save(publication);
        return saved;
    }

    public Optional<Publication> findByTitleContaining(String title) {
        return publicationRepository.findByTitleContaining(title);
    }
}
