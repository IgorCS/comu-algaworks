package com.algaworks.financeiro.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

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
public class ConsultaLancamentosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Lancamentos lancamentosRepository;

	@Inject
	private CadastroLancamentos cadastro;

	@Inject
	private Pessoas pessoas;

	@Inject
	private Usuarios usuarios;

	private List<Lancamento> lancamentos;

	private List<Lancamento> extrato;

	private BigDecimal total;

	private Date dataInicial;

	// @Temporal(TemporalType.DATE)
	private Date dataFinal;

	private BigDecimal lucro;
	
	private Double lucroJunit;

	

	private BigDecimal saldoNegativos;

	public BigDecimal getSaldoNegativos() {
		return saldoNegativos;
	}

	public void setSaldoNegativos(BigDecimal saldoNegativos) {
		this.saldoNegativos = saldoNegativos;
	}

	private Lancamento lancamentoSelecionado;

	private TipoLancamento tipoLancamento;

	private Lancamento lancamento;

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

	private List<Pessoa> todasPessoas;

	private List<Usuario> todosUsuarios;

	private UserSistema appuser;

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
			this.consultar();

			context.addMessage(null, new FacesMessage("Lançamento excluído com sucesso!"));
		} catch (NegocioException e) {

			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public void consultar() {

		if (this.saldoNegativos == null || this.saldoNegativos != null) {
			saldoNegativos = lancamentosRepository.saldoNegativo();
		}

		if (this.total == null || this.total != null) {
			total = lancamentosRepository.Lucro(0);
		}

		if (this.saldoNegativos != null && this.total != null) {

			lucro = total.add(saldoNegativos);

		}

		if (this.saldoNegativos != null && this.total == null) {

			lucro = null;

		}

		if (this.saldoNegativos == null && this.total != null) {

			lucro = total;

		}
		if (this.saldoNegativos == null && this.total == null && lucro != null) {

			lucro = null;

		}

		

		System.out.println("RESULTADO DA BAGAÇA: " + lucro);		
		this.lancamentos = lancamentosRepository.lancamentos();
		this.todasPessoas = this.pessoas.todasPessoas();
		this.lancamento = new Lancamento();
		this.pessoa = new Pessoa();
		this.todosUsuarios = this.usuarios.todosUsuarios();

		this.usuario = new Usuario();
		return;
	}

	public void calculaLucro() {
		
		this.lancamentos = lancamentosRepository.lancePessoa();
		if (this.saldoNegativos != null && this.total != null) {
			lucro = total.add(saldoNegativos);
		}

		System.out.println("Verifica" + lucro);

		

	}

	public void lancamentos() {

		this.lancamentos = lancamentosRepository.lancePessoa();
		this.todasPessoas = this.pessoas.todasPessoas();
		this.todosUsuarios = this.usuarios.todosUsuarios();
		this.lancamento = new Lancamento();
		this.pessoa = new Pessoa();
		this.usuario = new Usuario();
		

	}

	

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	

	public Lancamento getLancamentoSelecionado() {
		return lancamentoSelecionado;
	}

	public void setLancamentoSelecionado(Lancamento lancamentoSelecionado) {
		this.lancamentoSelecionado = lancamentoSelecionado;
	}

	

	public void calcula() {
		
		total = lancamentosRepository.calculaTotalMovimentado(tipoLancamento, pessoa);
		
	}

	

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getLucro() {
		return lucro;
	}

	public void setLucro(BigDecimal lucro) {
		this.lucro = lucro;
	}
	
	public Double getLucroJunit() {
		return lucroJunit;
	}

	public void setLucroJunit(Double lucroJunit) {
		this.lucroJunit = lucroJunit;
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

	

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}
	
	

}
