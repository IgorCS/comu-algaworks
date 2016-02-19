package com.algaworks.financeiro.ex_2;

public class Valor {
	private int valor;

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	private int total;

	public Valor(int valor) {
		this.valor = valor;
		this.total = 0;

	}

	// abaixo teríamos todos os métodos construtores get e set...
}