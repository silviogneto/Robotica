package model;

public class NoArvore {
	private NoArvore pai;
	
	private NoArvore frente;
	private NoArvore esquerda;
	private NoArvore direita;
	
	private char ultimoMovimento;
	
	public NoArvore(NoArvore pai, char ultimoMovimento) {
		this.pai = pai;
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

	public NoArvore getPai() {
		return pai;
	}

	public void setPai(NoArvore pai) {
		this.pai = pai;
	}

	public char getUltimoMovimento() {
		return ultimoMovimento;
	}

	public void setUltimoMovimento(char ultimoMovimento) {
		this.ultimoMovimento = ultimoMovimento;
	}
}