package com.algaworks.financeiro.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.financeiro.model.Pessoa;
import com.algaworks.financeiro.service.CadastroPessoas;
import com.algaworks.financeiro.service.NegocioException;

@Named
@javax.faces.view.ViewScoped
public class CadastroPessoaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroPessoas cadastroPessoas;

	@Inject
	private Pessoa pessoa;

	//private Pessoa pessoa;
	private List<Pessoa> listaTodas;

	public void prepararCadastroPessoa() {
		//this.listaTodas = this.pessoas.todasPessoas();

		if (this.pessoa == null) {
			this.pessoa = new Pessoa();
		}
	}

	public void salvarPessoa() {
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			this.cadastroPessoas.salvarPessoa(this.pessoa);

			this.pessoa = new Pessoa();
			context.addMessage(null, new FacesMessage("Pessoa cadastrado(a) com sucesso!"));
		} catch (NegocioException e) {

			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public List<Pessoa> getTodasPessoas() {
		return this.listaTodas;
	}

	/*
	 * public TipoLancamento[] getTiposLancamentos() { return
	 * TipoLancamento.values(); }
	 */

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}
