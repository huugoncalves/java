package br.com.huugoncalves.OrangeTalents.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.huugoncalves.OrangeTalents.controller.dto.PessoaRq;
import br.com.huugoncalves.OrangeTalents.controller.dto.PessoaRs;
import br.com.huugoncalves.OrangeTalents.model.Pessoa;
import br.com.huugoncalves.OrangeTalents.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
	
	private final PessoaRepository pessoaRepository;
	public PessoaController(PessoaRepository pessoaRepository) {
		this.pessoaRepository = pessoaRepository;
	}
	
	@GetMapping("/")
	public List<PessoaRs> findAll(){
		var pessoas = pessoaRepository.findAll();
		return pessoas.stream().map(PessoaRs::converter).collect(Collectors.toList());
	}
	
}