package com.algaworks.financeiro.test;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.algaworks.financeiro.controller.ConsultaLancamentosBean;
import com.algaworks.financeiro.controller.UsuarioController;
import com.algaworks.financeiro.model.TipoAutorizacao;
import com.algaworks.financeiro.model.Usuario;
import com.algaworks.financeiro.util.AppUser;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class ControllerTest {

	@Test
	public void TestUnit() {
		Pagamento pgmnt = new Pagamento();
		pgmnt.setDescricao("descricao");
		Assert.assertEquals("descricao", pgmnt.getDescricao());
		System.out.println("DSEC: " + pgmnt.getDescricao());

	}

	/*
	 * TipoAutorizacao spoon : TipoAutorizacao.values()
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void TESTE_Autorizacao() {
		for (TipoAutorizacao spoon : TipoAutorizacao.values()) {
			TipoAutorizacao poly = TipoAutorizacao.USUARIOS;
			TipoAutorizacao poly_Aut = TipoAutorizacao.ADMINISTRADORES;
			System.out.print(" Percorrendo o Anonymous: " + spoon + " = " + spoon.getNome() + "\n" + "\n"
					+ " Nome do ANONYMOUS: " + poly_Aut.name() + " & " + poly.name());
		}
	}
	
	
	

	@SuppressWarnings("deprecation")
	@Test
	public void lucroJunit() {
		ConsultaLancamentosBean lucro = new ConsultaLancamentosBean();
		lucro.setLucroJunit(10.001);
		Assert.assertEquals(10.001, lucro.getLucroJunit());
		System.out.println("TOTAL: " + lucro.getLucroJunit());

	}
	

	
	



}
