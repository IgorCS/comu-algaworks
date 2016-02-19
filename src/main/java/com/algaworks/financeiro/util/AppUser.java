package com.algaworks.financeiro.util;

import com.algaworks.financeiro.model.TipoAutorizacao;

public class AppUser {

	private String grupoNome;

	public String getGrupoNome() {
		return grupoNome;

	}

	public void setNome(String grupoNome) {
		this.grupoNome = grupoNome;
	}

	public void add(String grupoNome) {
		for (TipoAutorizacao spoon : TipoAutorizacao.values()) {
			 TipoAutorizacao poly = TipoAutorizacao.USUARIOS;
			//TipoAutorizacao poly = TipoAutorizacao.ADMINISTRADORES;
			System.out.print(" Percorrendo o Anonymous: " + spoon + " = " + spoon.getNome() + "\n"
					+ " Nome do ANONYMOUS: " + poly.name());
			this.grupoNome = poly.name();
		}
	}
}
