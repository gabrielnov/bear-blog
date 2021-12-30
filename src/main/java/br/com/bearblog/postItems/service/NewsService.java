package br.com.bearblog.postItems.service;

import br.com.bearblog.postItems.dto.NewsDto;
import br.com.bearblog.postItems.model.News;
import br.com.bearblog.postItems.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService {

    @Autowired
    NewsRepository newsRepository;

    public List<News> findAll() {
        return newsRepository.findAll();
    }

    public void saveNews(NewsDto news) {
        News newsModel = news.newsFactory();
        newsRepository.save(newsModel);
    }

    public Optional<News> findById(Long id) {
        return newsRepository.findById(id);
    }
}
