
public class TestadoraEnum {
	public static void main(String[] args) {
		for (OpcoesMenu op : OpcoesMenu.values()) {
			System.out.print("Menu " + op + " = " + op.getValor() + "\n");
		}
	}
}