package com.algaworks.financeiro.ex_2;

public class Soma {
	public static void main(String[] args) {
		double[] valores = new double[6];

		// inicializa os elementos do array
		valores[0] = 23;
		valores[1] = 65;
		valores[2] = 2;
		valores[3] = 1;
		valores[4] = 04.08999;
		valores[5] = 01.90909;

		// obtém a soma
		int soma = soma(valores);

		System.out.println("A soma dos valores é: " + soma);

		System.exit(0);
	}

	public static int soma(double[] valores) {
		int total = 0;
		for (int i = 0; i < valores.length; i++) {
			total += valores[i];
		}

		return total;
	}
}
