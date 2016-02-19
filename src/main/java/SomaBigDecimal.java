import java.math.BigDecimal;

public class SomaBigDecimal {

	public static void main(String args[]) {

		System.out.println("Operações básicas:");
		BigDecimal numeroParaOperacoes = new BigDecimal(20.07);

		// Operação de soma x.add(y)
		System.out.println("Soma: " + numeroParaOperacoes.add(BigDecimal.valueOf(0)));

		// Operação de soma x.substract(y)
		// System.out.println("Subtração:
		// "+numeroParaOperacoes.subtract(BigDecimal.TEN));

		// Operacao de divisao x.divide(y)
		// System.out.println("Divisão:
		// "+numeroParaOperacoes.divide(BigDecimal.TEN));

		// Opeação de multiplicação x
		// System.out.println("Multiplicao:
		// "+numeroParaOperacoes.multiply(BigDecimal.TEN));
	}

}