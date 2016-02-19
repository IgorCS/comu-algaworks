package com.algaworks.financeiro.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.algaworks.financeiro.model.Lancamento;
import com.algaworks.financeiro.model.Pessoa;
import com.algaworks.financeiro.model.TipoLancamento;
import com.algaworks.financeiro.model.Usuario;
import com.algaworks.financeiro.repository.Lancamentos;
import com.algaworks.financeiro.repository.Pessoas;
import com.algaworks.financeiro.repository.Usuarios;
import com.algaworks.financeiro.security.UserSistema;
import com.algaworks.financeiro.service.CadastroLancamentos;
import com.algaworks.financeiro.service.NegocioException;

@Named
@SessionScoped
public class ExtratoLancamentosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Lancamentos lancamentosRepository;

	@Inject
	private CadastroLancamentos cadastro;

	@Inject
	private Pessoas pessoas;

	@Inject
	private Usuarios usuarios;

	private List<Lancamento> extrato;

	private BigDecimal totalFiltro;

	private Date dataInicial;

	// @Temporal(TemporalType.DATE)
	private Date dataFinal;

	private BigDecimal lucroFiltro;

	private BigDecimal saldoNegativoFiltro;

	public BigDecimal getSaldoNegativoFiltro() {
		return saldoNegativoFiltro;
	}

	public void setSaldoNegativoFiltro(BigDecimal saldoNegativoFiltro) {
		this.saldoNegativoFiltro = saldoNegativoFiltro;
	}

	private Lancamento lancamentoSelecionado;

	private TipoLancamento tipoLancamento;

	private Lancamento lancamento;

	// private String nome;

	/* PEGA PELO NOME PESSOA */

	private Pessoa pessoa;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	// POR NOME
	/*
	 * public String getNome() { return nome; }
	 * 
	 * public void setNome(String nome) { this.nome = nome; }
	 */

	private List<Pessoa> todasPessoas;

	private List<Usuario> todosUsuarios;

	private UserSistema appuser;

	// public UserSistema usuariosessao;

	public UserSistema getAppuser() {
		return appuser;
	}

	public void buscaLanc() {
		this.todasPessoas = this.pessoas.todas();

		if (this.lancamento == null) {
			this.lancamento = new Lancamento();
		}
	}

	public void excluir() {
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			this.cadastro.excluir(this.lancamentoSelecionado);
			this.consultarExtrato();

			context.addMessage(null, new FacesMessage("Lançamento excluído com sucesso!"));
		} catch (NegocioException e) {

			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}
	
	public void dataStart_END(AjaxBehaviorEvent event) {
		if (this.dataInicial == null||this.dataFinal == null) {
			
			
		}
	}

	public void consultarExtrato() {

		if (this.saldoNegativoFiltro == null || this.saldoNegativoFiltro != null) {
			saldoNegativoFiltro = lancamentosRepository.saldoNegativoFiltro(dataInicial, dataFinal);
		}

		if (this.totalFiltro == null || this.totalFiltro != null) {
			totalFiltro = lancamentosRepository.LucroFiltro(dataInicial, dataFinal);
		}

		if (this.saldoNegativoFiltro != null && this.totalFiltro != null) {

			lucroFiltro = totalFiltro.add(saldoNegativoFiltro);

		}

		if (this.saldoNegativoFiltro != null && this.totalFiltro == null) {

			lucroFiltro = null;

		}

		if (this.saldoNegativoFiltro == null && this.totalFiltro != null) {

			lucroFiltro = totalFiltro;

		}
		if (this.saldoNegativoFiltro == null && this.totalFiltro == null && lucroFiltro != null) {

			lucroFiltro = null;

		}

		// BigDecimal bigResult = total.add(saldoNegativos);
		System.out.println("RESULTADO DA BAGAÇA: " + lucroFiltro);
		// AppUserDetailsService appUser = new AppUserDetailsService();
		// this.lancamentos = lancamentosRepository.lancePessoa(pessoa);
		// this.lancamentos = lancamentosRepository.lancamentos();
		this.extrato = lancamentosRepository.extrato(dataInicial, dataFinal);
		this.todasPessoas = this.pessoas.todasPessoas();
		this.lancamento = new Lancamento();
		this.pessoa = new Pessoa();
		this.todosUsuarios = this.usuarios.todosUsuarios();
		this.usuario = new Usuario();
		return;
	}

	public void calculaLucro() {
		// FacesContext context = FacesContext.getCurrentInstance();
		// Aqui faz a busca em lancamento pela Pessoa
		// this.lancamentos = lancamentosRepository.lancePessoa();
		if (this.saldoNegativoFiltro != null && this.totalFiltro != null) {
			lucroFiltro = totalFiltro.add(saldoNegativoFiltro);
		}

		System.out.println("Verifica" + lucroFiltro);

		// lucro = lancamentosRepository.lucroTotal(tipoLancamento, usuario);

		/*
		 * total = lancamentosRepository.Lucro();
		 * 
		 * saldoNegativos = lancamentosRepository.saldoNegativo();
		 * 
		 * lucro = total.add(saldoNegativos);
		 * 
		 * BigDecimal bigResult = total.add(saldoNegativos);
		 * 
		 * System.out.println("RESULTADO DA BAGAÇA: "+bigResult);
		 */

	}

	public void extrato() {

		// this.lancamentos = lancamentosRepository.lancePessoa();
		// this.lancamentos = lancamentosRepository.todosLanc();
		this.todasPessoas = this.pessoas.todasPessoas();
		this.todosUsuarios = this.usuarios.todosUsuarios();
		this.lancamento = new Lancamento();
		this.pessoa = new Pessoa();
		this.usuario = new Usuario();
		this.extrato = this.lancamentosRepository.extrato(dataInicial, dataFinal);

	}

	/*
	 * public void dataInicial(AjaxBehaviorEvent event) { if (
	 * //this.lancamento.getDataVencimento() == null this.extrato == null ) {
	 * this.lancamento.getDataVencimento();
	 * //this.lancamento.getDataVencimento() } }
	 * 
	 * 
	 * public void dataFinal(AjaxBehaviorEvent event) { if
	 * (this.lancamento.getDataVencimento() == null) {
	 * this.lancamento.getDataVencimento();
	 * //this.lancamento.getDataVencimento() } }
	 */
	@Temporal(TemporalType.DATE)
	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
		return;
	}

	@Temporal(TemporalType.DATE)
	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
		return;
	}

	public List<Lancamento> getExtrato() {
		return extrato;
	}

	public Lancamento getLancamentoSelecionado() {
		return lancamentoSelecionado;
	}

	public void setLancamentoSelecionado(Lancamento lancamentoSelecionado) {
		this.lancamentoSelecionado = lancamentoSelecionado;
	}

	/*
	 * public List<String> pesquisarDescricoes(String descricao) { return
	 * this.lancamentos.descricoesQueContem(descricao); }
	 */

	public void calcula() {
		// total = lancamentosRepository.calculaTotalMovimentado(tipoLancamento,
		// pessoa);
		totalFiltro = lancamentosRepository.calculaTotalMovimentado(tipoLancamento, pessoa);
		// lucro = lancamentosRepository.lucroTotal(pessoa);
	}

	// dataFinal

	public BigDecimal getTotalFiltro() {
		return totalFiltro;
	}

	public void setTotalFiltro(BigDecimal totalFiltro) {
		this.totalFiltro = totalFiltro;
	}

	public BigDecimal getLucroFiltro() {
		return lucroFiltro;
	}

	public void setLucroFiltro(BigDecimal lucroFiltro) {
		this.lucroFiltro = lucroFiltro;
	}

	public TipoLancamento getTipoLancamento() {
		return tipoLancamento;
	}

	public void setTipoLancamento(TipoLancamento tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
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

	/**
	 * @return the pessoa
	 */

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

}
