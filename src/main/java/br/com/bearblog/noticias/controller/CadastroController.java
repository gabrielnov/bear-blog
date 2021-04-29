package br.com.bearblog.noticias.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.bearblog.noticias.dto.RequisicaoNovoUser;
import br.com.bearblog.noticias.model.Autor;
import br.com.bearblog.noticias.repository.AutorRepository;

@Controller
@RequestMapping("cadastro")
public class CadastroController {
	
	@Autowired
	AutorRepository repository;
	
	@GetMapping
	@RequestMapping("form")
	public String cadastro(RequisicaoNovoUser requisicao) {
		return "user/form";
	}

	@PostMapping
	@RequestMapping("completo")
	public String novoUser(@Valid RequisicaoNovoUser requisicao) {
		Autor autor = requisicao.toUser();
		repository.save(autor);
		
		return "redirect:/home";
	}
}
