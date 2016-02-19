package com.algaworks.financeiro.ex_1;

public interface ControleRemoto {
    /*
     * Perceba que apenas a assinatura dos métodos estão aqui.
     * E cada método termina com um ponto-e-vírgula (;)
     */
    void mudarCanal(int canal);
    void aumentarVolume (int taxa);
    void diminuirVolume (int taxa);
    void ligar(boolean ligar);
    void desligar(boolean desligar);
   /* void ligar(boolean ON);
    void desligar(boolean OFF);*/
}
