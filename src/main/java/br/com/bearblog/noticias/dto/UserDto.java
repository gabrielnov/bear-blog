package br.com.bearblog.noticias.dto;

import java.util.Optional;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.bearblog.noticias.model.User;
import br.com.bearblog.noticias.repository.UserRepository;

public class UserDto {
	
	
	@Size(min=5, message = "O nome deve ter no m�nimo 5 caracteres!")	
	@Size(max = 50, message = "Um baita nome, em? Tente inserir um nome com menos de 50 caracteres.")
	private String nome;
	
	@NotBlank(message = "O email � obrigat�rio!")
	@Email(message = "Insira um endere�o de e-mail v�lido!")
	private String email;
	
	@Size(min=6, message= "A senha deve ter no m�nimo 6 caracteres!")
	private String senha;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public User toUser(UserRepository userRepository) {
		Optional<User> existente = userRepository.findByEmail(email);
		if(existente.isPresent()) {
			
		}
		
		User user = new User();
		user.setName(nome);
		user.setEmail(email);
		user.setPassphrase(new BCryptPasswordEncoder().encode(senha));
		
		return user;
	}
	


}
