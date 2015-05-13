package model;

public class NoArvore {
	private NoArvore frente, esquerda, direita;
	private char ultimoMovimento;
	
	public NoArvore(char ultimoMovimento) {
		this.ultimoMovimento = ultimoMovimento;
		this.frente = null;
		this.esquerda = null;
		this.direita = null;
	}
	
	public NoArvore getFrente() {
		return frente;
	}
	public void setFrente(NoArvore frente) {
		this.frente = frente;
	}
	
	public NoArvore getEsquerda() {
		return esquerda;
	}
	public void setEsquerda(NoArvore esquerda) {
		this.esquerda = esquerda;
	}
	
	public NoArvore getDireita() {
		return direita;
	}
	public void setDireita(NoArvore direita) {
		this.direita = direita;
	}
	
	public char getUltimoMovimento() {
		return ultimoMovimento;
	}
	public void setUltimoMovimento(char ultimoMovimento) {
		this.ultimoMovimento = ultimoMovimento;
	}
	
	
}
