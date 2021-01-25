package br.com.huugoncalves.OrangeTalents.service;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.huugoncalves.OrangeTalents.controller.dto.PessoaRq;
import br.com.huugoncalves.OrangeTalents.model.Pessoa;
import br.com.huugoncalves.OrangeTalents.repository.PessoaRepository;

public class CadastroPessoa {
	
	
	private PessoaRepository pessoaRepository;

	public CadastroPessoa(PessoaRepository pessoaRepository) {
		this.pessoaRepository = pessoaRepository;
	}
	
	@PostMapping("/")
	public void savePerson(@Valid @RequestBody PessoaRq pessoa) {
		var p = new Pessoa();
		p.setNome(pessoa.getNome());
		p.setEmail(p.getEmail());
		p.setDataNascimento(p.getDataNascimento());
		p.setCpf(p.getCpf());
		pessoaRepository.save(p);		
	}
	
}
