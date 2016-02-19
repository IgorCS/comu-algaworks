package com.algaworks.financeiro.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.hibernate.Session;
import org.springframework.format.annotation.DateTimeFormat;

import com.algaworks.financeiro.repository.Lancamentos;
import com.algaworks.financeiro.util.jsf.FacesUtil;
import com.algaworks.financeiro.util.report.ExecutorRelatorio;

@Named
@RequestScoped
public class RelatorioLancamentosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	private Date dataInicial;

	@Temporal(TemporalType.DATE)
	private Date dataFinal;

	 
	private String nome;

	private BigDecimal totalFiltro;

	private BigDecimal saldoNegativoFiltro;

	private BigDecimal lucroFiltro;

	@Inject
	private ExtratoLancamentosBean extratobean;

	@Inject
	private Lancamentos extrato;

	@Inject
	private FacesContext facesContext;

	@Inject
	private HttpServletResponse response;

	@Inject
	private EntityManager manager;

	public void emitir() {
		Map<String, Object> parametros = new HashMap<>();

		// parametros.put("usuario", this.id);
		parametros.put("nome", this.nome);
		parametros.put("saldoNegativoFiltro", this.saldoNegativoFiltro);
		parametros.put("lucroFiltro", this.lucroFiltro);
		parametros.put("totalFiltro", this.totalFiltro);
		parametros.put("dataInicial", this.dataInicial);
		parametros.put("dataFinal", this.dataFinal);

		System.out.println("saldoNegativoFiltro=>" + extratobean.getSaldoNegativoFiltro() + " LUCRO:"
				+ extratobean.getLucroFiltro() + " Total de Receitas:" + extratobean.getTotalFiltro()
				+" DATA INITIAL:" + extratobean.getDataInicial()
				+" DATA FINAL:" + extratobean.getDataFinal());

		ExecutorRelatorio executor = new ExecutorRelatorio("/relatorios/relatorioLancamentos.jasper", this.response,
				parametros, "Extrato.pdf");

		Session session = manager.unwrap(Session.class);
		session.doWork(executor);

		if (executor.isRelatorioGerado()) {
			facesContext.responseComplete();
			System.out.println("Executa Relatório: "+session);
		} else {
			FacesUtil.addErrorMessage("A execução do relatório não retornou dados.");
		}
	}
	/*
	 * @NotNull public Long getId() { return id; }
	 * 
	 * public void setId(Long id) { this.id = id; }
	 */

	@NotNull
	ConsultaLancamentosBean luc = new ConsultaLancamentosBean();

	public BigDecimal getLucroFiltro() {
		return lucroFiltro;
	}

	public void setLucroFiltro(BigDecimal lucroFiltro) {
		this.lucroFiltro = extratobean.getLucroFiltro();
	}

	@NotNull
	ExtratoLancamentosBean tot = new ExtratoLancamentosBean();

	public BigDecimal getTotalFiltro() {
		return extratobean.getTotalFiltro();
	}

	public void setTotalFiltro(BigDecimal totalFiltro) {
		this.totalFiltro = extratobean.getTotalFiltro();
	}

	// lancamentos.Lucro(15)

	@NotNull
	ExtratoLancamentosBean sneg = new ExtratoLancamentosBean();

	public BigDecimal getSaldoNegativoFiltro() {
		return extratobean.getSaldoNegativoFiltro();
	}

	public void setSaldoNegativoFiltro(BigDecimal saldoNegativoFiltro) {
		this.saldoNegativoFiltro = extratobean.getSaldoNegativoFiltro();
	}

	@NotNull
	UsuarioController user = new UsuarioController();

	public String getNome() {
		return user.getUsuario().getUsername();
	}

	public void setNome(String nome) {
		this.nome = user.getUsuario().getUsername();
	}

	@NotNull
	ExtratoLancamentosBean dateInit = new ExtratoLancamentosBean();
	@Temporal(TemporalType.DATE)	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	public Date getDataInicial() {
		return extratobean.getDataInicial();
	}
    
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	@NotNull
	ExtratoLancamentosBean dateEnd = new ExtratoLancamentosBean();
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	public Date getDataFinal() {
		return extratobean.getDataFinal();
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

}