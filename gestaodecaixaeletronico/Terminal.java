package poo.gestaodecaixaeletronico;

import java.util.Scanner;

public class Terminal {

	private Caixa meuCaixa;
	private int modoAtual;

	public Terminal(CadastroContas bd) {
		this.meuCaixa = new Caixa(this, bd);
	}

	public void iniciarOperacao() {
		int opcao;
		opcao = this.getOpcao();
		while (opcao != 6) {
			switch (opcao) {
			case 1:
				double saldo = this.meuCaixa.consultaSaldo(getInt("numero da Conta"), getInt("Senha"));
				if (saldo != -1) {
					System.out.println("Saldo Atual:" + saldo);
				} else {
					System.out.println("Conta/senha invalida");
				}
				break;
			case 2:
				boolean b = this.meuCaixa.efetuaSaque(getInt("numero da conta"), (double) getInt("Valor"),
						getInt("Senha"));
				if (b) {
					System.out.println("Retire o dinheiro");
				} else {
					System.out.println("Pedido de saque recusado");
				}
				break;	
			case 3:
				boolean c = this.meuCaixa.depositoDinheiro(getInt("numero da conta"), (double) getInt("Valor"));
				if (c) {
					System.out.println("Dinheiro Depositado");
				}else {
					System.out.println("conta nao encontrada/valor invalido");
				}
				break;
			case 4:
				boolean d = this.meuCaixa.depositoCheque(getInt("numero da conta"), (double) getInt("Valor"));
				if (d) {
					System.out.println("Cheque Depositado");
				}else {
					System.out.println("conta nao encontrada/valor invalido");
				}
				break;
			case 5:
				boolean e = this.meuCaixa.transferencia(getInt("numero da conta origem"), (double) getInt("Valor"),
						getInt("Senha"),getInt("numero da conta destino"));
						if (e) {
							System.out.println ("Transferencia concluida");
						}else {
							System.out.println("Transferencia nao concluida");
						}
						
			case 7:
				this.meuCaixa.recarrega();
				break;
			}
			opcao = getOpcao();
		}
	}

	public void setModo(int modo) {
		if (modo == 0 || modo == 1) {
			this.modoAtual = modo;
		}
	}

	private int getOpcao() {
		int opcao;
		do {
			if (this.modoAtual == 1) {
				opcao = getInt("Opcao: 1 - Consulta Saldo, 2 - Saque, 3 - Deposito Em Dinheiro, 4 -  Deposito Em Cheque, 5 - Transferencia  , 6 - Sair");
				if (opcao != 1 & opcao != 2 & opcao != 3 & opcao != 4 & opcao != 5 & opcao != 6) {
					opcao = 0;
				}
			} else {
				opcao = getInt("Opcao : 6 - Sair , 7 - Recarrega,");
				if (opcao != 6 & opcao != 7) {
					opcao = 0;
				}
			}
		} while (opcao == 0);

		return opcao;
	}

	private int getInt(String string) {
		Scanner r = new Scanner(System.in);
		System.out.println("Entre com " + string);

		if (r.hasNextInt()) {
			return r.nextInt();

		}
		String st = r.next();
		System.out.println("Erro na leitura de Dados");
		return 0;
	}

}
