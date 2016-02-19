package com.algaworks.financeiro.repository;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import com.algaworks.financeiro.controller.UsuarioController;
import com.algaworks.financeiro.model.Lancamento;
import com.algaworks.financeiro.model.Pessoa;
import com.algaworks.financeiro.model.TipoLancamento;
import com.algaworks.financeiro.model.Usuario;

public class Lancamentos implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	@Inject
	public Lancamentos(EntityManager manager) {
		this.manager = manager;
	}

	public Lancamento porId(Long id) {
		return manager.find(Lancamento.class, id);
	}

	public List<String> descricoesQueContem(String descricao) {
		TypedQuery<String> query = manager.createQuery(
				"select distinct descricao from Lancamento " + "where upper(descricao) like upper(:descricao)",
				String.class);
		query.setParameter("descricao", "%" + descricao + "%");
		return query.getResultList();
	}

	public List<Lancamento> lancamentoUsuario(Usuario usuario) {
		TypedQuery<Lancamento> query = manager.createQuery("from Lancamento l where l.usuario=:usuario ",
				Lancamento.class);
		query.setParameter("usuario", usuario);
		return query.getResultList();
	}

/*
 //Nesta TypedQuery faço a consulta que vai ser mapeada no metódo verificaLoginDisponibilidade.
	public List<String> usuarioExiste(String usuarioLogin) {
		TypedQuery<String> query = manager.createQuery(
				"select distinct nome from Usuario " ,
		         // + "where upper(nome) "
				//+ "like upper(:usuarioLogin)",
				String.class);
		//query.setParameter("usuarioLogin", "%" + "" + "%");
		return query.getResultList();
	}
 */

	public List<Lancamento> lancamentos(String nome){
		UsuarioController user = new UsuarioController();
		TypedQuery<Lancamento> query = manager.createQuery(
				"select l FROM Lancamento l INNER JOIN l.usuario u"
				+ " WHERE u.nome=:nome", Lancamento.class);
         query.setParameter("nome",user.getUsuario().getUsername());
		 return query.getResultList();

	}

	public BigDecimal saldoNegativo(String nome) {
		UsuarioController user = new UsuarioController();
		String jpql = "select sum(-l.valor) from Lancamento l INNER JOIN l.usuario u "
		 +"where u.nome=:nome and l.tipo='DESPESA'";
		javax.persistence.Query query = manager.createQuery(jpql);
		query.setParameter("nome",user.getUsuario().getUsername());
		return (BigDecimal) ((javax.persistence.Query) query).getSingleResult();
	}

	public BigDecimal Lucro(int i) {
		UsuarioController user = new UsuarioController();
		String jpql = "select sum(l.valor) from Lancamento l INNER JOIN l.usuario u" + " where u.nome=" + "'"
				+ user.getUsuario().getUsername() + "'" + " AND l.tipo='RECEITA' ";

		javax.persistence.Query query = manager.createQuery(jpql);

		return (BigDecimal) ((javax.persistence.Query) query).getSingleResult();
	}

	public List<Lancamento> extrato(Date dataInicial, Date dataFinal,String nome) {

		UsuarioController user = new UsuarioController();

		TypedQuery<Lancamento> query = manager.createQuery(
				"select l FROM Lancamento l INNER JOIN l.usuario u WHERE "
				+ "nome=:nome"
				+ " AND  data_vencimento BETWEEN (:dataInicial) "
				+ " AND (:dataFinal) "

		, Lancamento.class);		
		query.setParameter("nome",user.getUsuario().getUsername());
		query.setParameter("dataInicial", dataInicial, TemporalType.DATE);
		query.setParameter("dataFinal",   dataFinal, TemporalType.DATE);
		return query.getResultList();
	}

	public BigDecimal LucroFiltro(Date dataInicial, Date dataFinal,String nome) {
		UsuarioController user = new UsuarioController();
		String jpql = "select sum(l.valor) from Lancamento l INNER JOIN l.usuario u" + " where u.nome=:nome"
		+" AND l.tipo='RECEITA' AND  data_vencimento BETWEEN (:dataInicial) AND (:dataFinal) ";
		javax.persistence.Query query = manager.createQuery(jpql);
		query.setParameter("nome",user.getUsuario().getUsername());
		query.setParameter("dataInicial", dataInicial, TemporalType.DATE);
		query.setParameter("dataFinal", dataFinal, TemporalType.DATE);
		return (BigDecimal) ((javax.persistence.Query) query).getSingleResult();
	}

	public BigDecimal saldoNegativoFiltro(Date dataInicial, Date dataFinal) {
		UsuarioController user = new UsuarioController();
		String jpql = "select sum(-l.valor) from Lancamento l INNER JOIN l.usuario u " 
		        + " where u.nome=" + "'"
				+ user.getUsuario().getUsername() + "'"
				+ " AND  data_vencimento BETWEEN (:dataInicial) "
				+ " AND  (:dataFinal)     " 
				+ " AND  l.tipo='DESPESA' ";
		javax.persistence.Query query = manager.createQuery(jpql);
		query.setParameter("dataInicial", dataInicial, TemporalType.DATE);
		query.setParameter("dataFinal", dataFinal, TemporalType.DATE);
		return (BigDecimal) ((javax.persistence.Query) query).getSingleResult();
	}

	public List<Lancamento> lancePessoa() {
		UsuarioController user = new UsuarioController();
		TypedQuery<Lancamento> query = manager.createQuery("select l FROM Lancamento l INNER JOIN l.usuario u WHERE "
				+ "u.nome=" + "'" + user.getUsuario().getUsername() + "'", Lancamento.class);

		return query.getResultList();

	}

	public List<Lancamento> todosLancamento(Usuario usuario) {
		TypedQuery<Lancamento> query = manager.createQuery("from Lancamento l where l.usuario=:usuario ",

				Lancamento.class);
		query.setParameter("usuario", usuario);

		return query.getResultList();
	}

	public BigDecimal calculaTotalMovimentado(TipoLancamento tipo, Pessoa pessoa) {
		String jpql = "select sum(l.valor) from Lancamento " + "l where l.pessoa = :pessoa and l.tipo= :tipo ";
		javax.persistence.Query query = manager.createQuery(jpql);
		query.setParameter("pessoa", pessoa);
		query.setParameter("tipo", tipo);
		return (BigDecimal) ((javax.persistence.Query) query).getSingleResult();
	}

	public BigDecimal lucroTotal() {

		String jpql = "select sum(l.valor) - (select sum(l.valor) "
				+ "FROM Lancamento l where l.tipo='DESPESA' and l.usuario=:usuario) "
				+ "FROM Lancamento l WHERE l.tipo='RECEITA' and l.usuario=:usuario";
		javax.persistence.Query query = manager.createQuery(jpql);

		return (BigDecimal) ((javax.persistence.Query) query).getSingleResult();
	}

	public void adicionar(Lancamento lancamento) {
		this.manager.persist(lancamento);
	}

	public Lancamento guarda(Lancamento lancamento) {
		return this.manager.merge(lancamento);
	}

	public void remover(Lancamento lancamento) {
		this.manager.remove(lancamento);
	}
}