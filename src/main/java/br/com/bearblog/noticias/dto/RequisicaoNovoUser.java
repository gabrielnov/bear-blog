package br.com.bearblog.noticias.dto;

import java.util.Optional;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.bearblog.noticias.model.Autor;
import br.com.bearblog.noticias.repository.AutorRepository;

public class RequisicaoNovoUser {
	
	
	@Size(min=5, message = "O nome deve ter no mínimo 5 caracteres!")	
	@Size(max = 50, message = "Um baita nome, em? Tente inserir um nome com menos de 50 caracteres.")
	private String nomeAutor;
	
	@NotBlank(message = "O email é obrigatório!")
	@Email(message = "Insira um endereço de e-mail válido!")
	private String email;
	
	@Size(min=6, message= "A senha deve ter no mínimo 6 caracteres!")
	private String senha;

	public String getNomeAutor() {
		return nomeAutor;
	}

	public void setNomeAutor(String nomeAutor) {
		this.nomeAutor = nomeAutor;
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

	public Autor toUser(AutorRepository autorRepository) {
		Optional<Autor> existente = autorRepository.findByEmail(email);
		if(existente.isPresent()) {
			
		}
		
		Autor autor = new Autor();
		autor.setNomeAutor(nomeAutor);
		autor.setEmail(email);
		autor.setSenha(new BCryptPasswordEncoder().encode(senha));
		
		return autor;
	}
	


}
