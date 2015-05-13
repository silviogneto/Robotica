package model;

public class Arvore {
	private NoArvore raiz;

	public Arvore(NoArvore raiz) {
		this.raiz = raiz;
	}
	
	public NoArvore getRaiz() {
		return raiz;
	}

	public void setRaiz(NoArvore raiz) {
		this.raiz = raiz;
	}
}
