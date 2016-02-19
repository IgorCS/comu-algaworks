package com.algaworks.financeiro.ex_1;

public class ExemploInterfaceamento {
    public static void main(String[] args) {
        ModeloTV001 tv1 = new ModeloTV001(21);
        ModeloSDX tv2 = new ModeloSDX (42);

        tv1.ligar(false);
        tv2.ligar(true);

        System.out.print("TV1 - modelo " + tv1.MODELO + " está ");
        System.out.println(tv1.isLigada() ? "ligada" : "desligada");
        System.out.print("TV2 - modelo " + tv2.MODELO + " está ");
        System.out.println(tv1.isLigada() ? "ligada" : "desligada");

        // ambas as TVs estão ligadas e vamos desligá-las
        System.out.println("Desligando modelo " + tv1.MODELO);
        tv1.desligar(false);
        
        System.out.println("Desligando modelo " + tv2.MODELO);
        tv2.desligar(true);
    }
}
