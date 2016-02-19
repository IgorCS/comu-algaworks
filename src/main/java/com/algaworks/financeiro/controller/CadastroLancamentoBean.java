package com.algaworks.financeiro.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.financeiro.model.Lancamento;
import com.algaworks.financeiro.model.Pessoa;
import com.algaworks.financeiro.model.TipoLancamento;
import com.algaworks.financeiro.model.Usuario;
import com.algaworks.financeiro.repository.Lancamentos;
import com.algaworks.financeiro.repository.Pessoas;
import com.algaworks.financeiro.repository.Usuarios;
import com.algaworks.financeiro.service.CadastroLancamentos;
import com.algaworks.financeiro.service.NegocioException;

@Named
@SessionScoped
public class CadastroLancamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroLancamentos cadastro;

	@Inject
	private Pessoas pessoas;

	@Inject
	private Usuarios usuarios;

	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Inject
	private Lancamentos lancamentos;

	private Lancamento lancamento;

	private List<Pessoa> todasPessoas;

	private List<Usuario> todosUsuarios;

	public void setTodasPessoas(List<Pessoa> todasPessoas) {
		this.todasPessoas = todasPessoas;
	}

	public void setTodosUsuarios(List<Usuario> todosUsuarios) {
		this.todosUsuarios = todosUsuarios;
	}

	public void prepararCadastro() {
		this.todasPessoas = this.pessoas.todas();
		this.todosUsuarios = this.usuarios.todosUsuarios();

		if (this.lancamento == null) {
			this.lancamento = new Lancamento();
		}
	}

	public List<String> pesquisarDescricoes(String descricao) {
		return this.lancamentos.descricoesQueContem(descricao);
	}

	public void dataVencimentoAlterada(AjaxBehaviorEvent event) {
		if (this.lancamento.getDataPagamento() == null) {
			this.lancamento.setDataPagamento(this.lancamento.getDataVencimento());
		}
	}

	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			this.cadastro.salvar(this.lancamento);

			this.lancamento = new Lancamento();
			context.addMessage(null, new FacesMessage("Lançamento cadastrado com sucesso!"));
		} catch (NegocioException e) {

			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public List<Pessoa> getTodasPessoas() {
		return this.todasPessoas;
	}

	public List<Usuario> getTodosUsuarios() {
		return this.todosUsuarios;
	}

	public TipoLancamento[] getTiposLancamentos() {
		return TipoLancamento.values();
	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

}