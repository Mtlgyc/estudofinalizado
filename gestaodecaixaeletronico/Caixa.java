package poo.gestaodecaixaeletronico;


import poo.gestaodecontas.Conta;

public class Caixa {
	
	private Terminal meuTerminal;
	private CadastroContas bdContas;
	private double saldo;
	
	public Caixa(Terminal terminal, CadastroContas bd){
		this.meuTerminal= terminal;
		this.bdContas=  bd;
	}
	
	public double consultaSaldo(int numeroDaConta, int senha) {
		Conta conta;
		conta= this.bdContas.buscaConta(numeroDaConta);
		if(conta != null) {
			return conta.verificaSaldo(senha);
			
		}else {
			return -1;
		}
		
	}
	public boolean efetuaSaque(int numeroDaConta,double valor,int senha) {
		
		if(valor<0 || (valor%50) != 0 || valor > 500 || valor>this.saldo) {
			return false;
		}
	
    Conta conta = bdContas.buscaConta(numeroDaConta);
	    if(conta ==null || ! conta.debitaValor(valor,senha,"saque automatico")) {
	    	return false;
	    }
    this.liberaCedulas((int)(valor/50));
    this.saldo-=valor;
    if(this.saldo<500) {
    	this.meuTerminal.setModo(0);
    }
    return true;
}
	public boolean depositoDinheiro(int numeroDaConta,double valor) {
		
		if(valor<=0) {
			return false;
		}
		Conta conta = bdContas.buscaConta(numeroDaConta);
	    if(conta == null || ! conta.creditaValor(valor,"deposito em especie automatico")) {
	    	return false;
		 }
	return true;
	}
	
	public boolean depositoCheque(int numeroDaConta,double valor) {
		
		if(valor<=0) {
			return false;
		}
		Conta conta = bdContas.buscaConta(numeroDaConta);
	    if(conta == null || ! conta.creditaValor(valor,"deposito com cheque automatico")) {
	    	return false;
		 }
		return true;
	}
	
	public boolean transferencia(int contaOrigem, double valor, int senha, int contaDestino) {
		if(valor<=0 || valor>this.saldo) {
			return false;
		}
	
    Conta c1 = bdContas.buscaConta(contaOrigem);
	    if(c1 ==null || ! c1.debitaValor(valor,senha,"transferencia automatico")) {
	    	return false;
	    }
	    Conta c2  = bdContas.buscaConta(contaDestino);
	    if(c2 == null || ! c2.creditaValor(valor,"transferencia automatico")) {
	    	return false;
		 }
	return true;
	}
	
	public String geraExtrato(int numeroDaConta, int senha) {
		Conta conta = bdContas.buscaConta(numeroDaConta);
		if(conta == null) {
			return null;
			}
		String String = conta.historico();
		return String;
	}
	

	public void recarrega() {
		this.saldo= 2000;
		this.meuTerminal.setModo(1);
}
	private void liberaCedulas(int quantidade) {
		while (quantidade-- > 0) {
			System.out.println("===/ R$ 50,00 /===");
		}
	}

}
