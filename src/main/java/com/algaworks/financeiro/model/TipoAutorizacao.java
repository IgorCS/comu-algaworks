package com.algaworks.financeiro.model;

public enum TipoAutorizacao {

	USUARIOS("USUARIOS"), 
	ADMINISTRADORES("ADMINISTRADORES");

	private String nome;
	
	TipoAutorizacao(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

}