package br.com.bearblog.noticias.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.bearblog.noticias.model.Autor;
import br.com.bearblog.noticias.model.Noticia;

public class RequisicaoNovoUser {
	
	@NotBlank
	private String nomeAutor;
	
	@NotBlank
	private String email;
	
	@NotBlank
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
		this.senha = new BCryptPasswordEncoder().encode(senha);
	}

	public Autor toUser() {
		Autor autor = new Autor();
		autor.setNomeAutor(nomeAutor);
		autor.setEmail(email);
		autor.setSenha(senha);
		
		return autor;
	}
	
//	Noticia noticia = new Noticia();
//	noticia.setNomeAutor(nomeAutor);
//	noticia.setTexto(texto);
//	noticia.setTitulo(titulo);
//	noticia.setData(LocalDateTime.now());
//	return noticia;
//}
//	

}
