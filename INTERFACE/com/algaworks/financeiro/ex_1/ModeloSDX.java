package com.algaworks.financeiro.ex_1;

public class ModeloSDX extends TV implements ControleRemoto {
	public final String MODELO = "TV-SDX";

	public ModeloSDX(int tamanho) {
		super(tamanho);
	}

	@Override
	public void desligar(boolean desligar) {
		System.out.println("DESLIGANDO!");
		super.setLigada(true);
	}

	public void ligar(boolean ligar) {
		System.out.println("BEM VINDO!");
		super.setLigada(true);
	}

	public void aumentarVolume(int taxa) {
	}

	public void diminuirVolume(int taxa) {
	}

	public void mudarCanal(int canal) {
	}

}
