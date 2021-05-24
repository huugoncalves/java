package br.com.huugoncalves.OrangeTalents.controller.dto;

import br.com.huugoncalves.OrangeTalents.model.Pessoa;

public class PessoaRs {	

	private Long id;
	private String nome;
	private String email;
	private String dataNascimento;
	private String cpf;

	public static PessoaRs converter(Pessoa p) {
		var pessoa = new PessoaRs();
		pessoa.setId(p.getId());
		pessoa.setNome(p.getNome());
		pessoa.setEmail(p.getEmail());
		pessoa.setDataNascimento(p.getDataNascimento());
		pessoa.setCpf(p.getCpf());
		return pessoa;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}

