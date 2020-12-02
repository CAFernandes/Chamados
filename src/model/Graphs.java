package model;

public class Graphs {
	private String valor;
	private int qtd;
	
	
	public Graphs(String valor, int qtd) {
		this.setValor(valor);
		this.setQtd(qtd);
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public int getQtd() {
		return qtd;
	}
	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
}
