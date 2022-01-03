package br.com.bearblog.postItems.service;

import br.com.bearblog.postItems.dto.PublicationDto;
import br.com.bearblog.postItems.model.Publication;
import br.com.bearblog.postItems.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicationService {

    @Autowired
    NewsRepository newsRepository;

    public List<Publication> findAll() {
        return newsRepository.findAll();
    }

    public Publication saveNews(PublicationDto news) {
        Publication newsModel = news.newsFactory();
        Publication saved = newsRepository.save(newsModel);
        return saved;
    }

    public Optional<Publication> findById(Long id) {
        return newsRepository.findById(id);
    }

    public Optional<Publication> findByTitleContaining(String title) {
        return newsRepository.findByTitleContaining(title);
    }
}
