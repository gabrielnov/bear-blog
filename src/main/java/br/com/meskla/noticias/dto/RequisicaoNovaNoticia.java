package br.com.meskla.noticias.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import br.com.meskla.noticias.model.Noticia;

public class RequisicaoNovaNoticia {
	

	@NotBlank
	private String autor;
	
	@NotBlank
	private String texto;
	
	private LocalDate data;

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	public Noticia toNoticia() {
		Noticia noticia = new Noticia();
		noticia.setAutor(autor);
		noticia.setTexto(texto);
		noticia.setData(LocalDate.now());
		return noticia;
	}
	
}


