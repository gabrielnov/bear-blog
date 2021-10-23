package br.com.bearblog.postItems.dto;

import br.com.bearblog.postItems.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDto {
	
	@Size(min=5, message = "Your name must contain 5 characters!")
	@Size(max = 50, message = "Insert a name with less than 50 characters!")
	private String name;
	
	@NotBlank(message = "E-mail address is mandatory!")
	@Email(message = "Insert a valid e-mail address!")
	private String email;
	
	@Size(min=6, message= "Your password must contain at least 6 characters!")
	private String passphrase;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassphrase() {
		return passphrase;
	}

	public void setPassphrase(String passphrase) {
		this.passphrase = passphrase;
	}

	public User toUser() {

		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setPassphrase(new BCryptPasswordEncoder().encode(passphrase));
		
		return user;
	}
}
