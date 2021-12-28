package br.com.bearblog.postItems.dto;

import br.com.bearblog.postItems.model.News;
import br.com.bearblog.postItems.model.User;
import br.com.bearblog.postItems.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Optional;

public class NewsDto {

	@NotNull	
	@Size(min=10, max=100, message = "Your title must contain at least 10 characters!")
	private String title;

	@NotNull
	@Size(min=1, max=250, message = "Your title must be between 1 and 250 characters!")
	private String text;

	private User user;	
	
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public News newsFactory(UserRepository repository) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Optional<User> currentUser = repository.findByEmail(userDetails.getUsername());
		
		News news = new News();
		
		news.setUser(currentUser.get());
		news.setText(text);
		news.setTitle(title);
		news.setDate(LocalDateTime.now());

		return news;
	}
	
}


