package br.com.bearblog.noticias.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import br.com.bearblog.noticias.model.Noticia;

public class RequisicaoNovaNoticia {
	
	@NotBlank
	private String titulo;
	
	@NotBlank
	private String nomeAutor;
	
	@NotBlank
	private String texto;

	public String getNomeAutor() {
		return nomeAutor;
	}

	public void setNomeAutor(String nomeAutor) {
		this.nomeAutor = nomeAutor;
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

	public Noticia toNoticia() {
		Noticia noticia = new Noticia();
		noticia.setNomeAutor(nomeAutor);
		noticia.setTexto(texto);
		noticia.setTitulo(titulo);
		noticia.setData(LocalDateTime.now());
		return noticia;
	}
	
}


