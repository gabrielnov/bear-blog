package br.com.bearblog.noticias.dto;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;

import br.com.bearblog.noticias.model.Autor;
import br.com.bearblog.noticias.model.Noticia;
import br.com.bearblog.noticias.repository.AutorRepository;

public class RequisicaoNovaNoticia {

	@NotNull	
	@Size(min=10, max=50, message = "Seu título deve ter entre 10 e 50 caracteres!")
	private String titulo;	

	@NotNull
	@Size(min=100, max = 500, message = "Sua notícia deve ter entre 100 e 500 caracteres!")
	private String texto;
	
	@NotNull
	private String imagem;
	
	private Autor autor;
	
	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}	
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public Noticia toNoticia(AutorRepository repository) {
		
	
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Optional<Autor> logado = repository.findByEmail(userDetails.getUsername());
		
		Noticia noticia = new Noticia();	
		
		noticia.setAutor(logado.get());
		noticia.setTexto(texto);
		noticia.setTitulo(titulo);
		noticia.setData(LocalDateTime.now());
		noticia.setImagem(imagem);
		return noticia;
	}
	
}


