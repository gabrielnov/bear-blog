package br.com.bearblog.postItems.dto;

import br.com.bearblog.postItems.model.News;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Optional;

public class NewsDto {

	public NewsDto(String title, String text) {
		this.title = title;
		this.text = text;
	}

	@NotNull
	@Size(min=10, max=100, message = "Your title must contain at least 10 characters!")
	private String title;

	@NotNull
	@Size(min=1, max=250, message = "Your title must be between 1 and 250 characters!")
	private String text;

	private String username;
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUsername() {
		return username;
	}

	public void setUser(String username) {
		this.username = username;
	}

	public News newsFactory() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		News news = new News();
		
		news.setUsername(username);
		news.setText(text);
		news.setTitle(title);
		news.setDate(LocalDateTime.now());

		return news;
	}
	
}


