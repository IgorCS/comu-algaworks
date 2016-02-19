package com.algaworks.financeiro.ex_1;

public class ModeloTV001 extends TV implements ControleRemoto {
    public final String MODELO = "TV001";

    public ModeloTV001(int tamanho) {
        super(tamanho);
    }
    
    @Override
	public void ligar(boolean ligar) {
		super.setLigada(true);
		
	}

	@Override
	public void desligar(boolean desligar) {
		 super.setLigada(true);
		
	}

    /*public void desligar() {
        super.setLigada(false);
    }

    public void ligar() {
        super.setLigada(true);
    }*/

    public void aumentarVolume(int taxa) { }
    public void diminuirVolume(int taxa) { }
    public void mudarCanal(int canal) { }

	
}
