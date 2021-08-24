package br.com.bearblog.noticias.dto;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.bearblog.noticias.model.User;
import br.com.bearblog.noticias.model.PostItem;
import br.com.bearblog.noticias.repository.UserRepository;

public class PostItemDto {

	@NotNull	
	@Size(min=10, max=100, message = "Seu t�tulo deve ter entre 10 e 100 caracteres!")
	private String title;
		
	@NotNull
	private String text;
		
	@NotNull
	private String image;
	
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
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public PostItem postItemConverter(UserRepository repository) {
		
	
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Optional<User> currentUser = repository.findByEmail(userDetails.getUsername());
		
		PostItem postItem = new PostItem();
		
		postItem.setUser(currentUser.get());
		postItem.setText(text);
		postItem.setTitle(title);
		postItem.setDate(LocalDateTime.now());
		postItem.setImage(image);
		return postItem;
	}
	
}


