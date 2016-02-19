package com.algaworks.financeiro.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.financeiro.model.Pessoa;
import com.algaworks.financeiro.repository.Pessoas;
import com.algaworks.financeiro.util.Transactional;

public class CadastroPessoas implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Pessoas pessoas;
	
	@Transactional
	public void salvarPessoa(Pessoa pessoa) throws NegocioException {
		if (pessoa.getNome() == null) {
			throw new NegocioException(
					"Campo Pessoa nao pode ser Vazio");
		}
		
		this.pessoas.adicionar(pessoa);
		//this.pessoas.guardar(pessoa);
	}
	
	
	
}