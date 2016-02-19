package com.algaworks.financeiro.ex_1;

public class TV {
    private int tamanho;
    public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	public int getCanal() {
		return canal;
	}

	public void setCanal(int canal) {
		this.canal = canal;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public boolean isLigada() {
		return ligada;
	}

	public void setLigada(boolean ligada) {
		this.ligada = ligada;
	}

	private int canal;
    private int volume;
    private boolean ligada;

    public TV(int tamanho) {
        this.tamanho = tamanho;
        this.canal = 0;
        this.volume = 0;
        this.ligada = false;
    }

    // abaixo teríamos todos os métodos construtores get e set...
}
