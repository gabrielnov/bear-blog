package br.com.bearblog.postItems.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class News {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	private String title;

	@Column(columnDefinition = "LONGTEXT")
	@Size(min = 1, max = 240)
	private String text;
	private LocalDateTime date;
	
	@NotNull 
	private String username;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime localDateTime) {
		this.date = localDateTime;
	}

}
