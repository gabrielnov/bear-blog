package br.com.meskla.noticias.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import br.com.meskla.noticias.model.Noticia;

public class RequisicaoNovaNoticia {
	

	@NotBlank
	private String nomeAutor;
	
	@NotBlank
	private String texto;
	
	private LocalDate data;



	public String getNomeAutor() {
		return nomeAutor;
	}

	public void setNomeAutor(String nomeAutor) {
		this.nomeAutor = nomeAutor;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	public Noticia toNoticia() {
		Noticia noticia = new Noticia();
		noticia.setNomeAutor(nomeAutor);
		noticia.setTexto(texto);
		noticia.setData(LocalDate.now());
		return noticia;
	}
	
}


