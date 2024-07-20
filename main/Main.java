package poo.main;

import poo.gestaodecontas.Conta;
import poo.gestaodecontas.Cliente;
import poo.gestaodecaixaeletronico.CadastroContas;
import poo.gestaodecaixaeletronico.Terminal;

public class Main {

	public static void main(String[] args) {

		Cliente cliente[] = { new Cliente("123", "Lidia"), new Cliente("123", "Joelma"), new Cliente("123", "Keila"),
				new Cliente("123", "Robson") };

		CadastroContas bd = new CadastroContas(4);

		for (int i = 0; i < cliente.length; i++) {
			bd.adicionaConta(new Conta(i+1, cliente[i], 123, 1000.0));

		}

		Terminal c1 = new Terminal(bd);
		c1.iniciarOperacao();
	}

}
