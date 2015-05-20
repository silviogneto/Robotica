package model;

public class Arvore {
	private NoArvore raiz;
	private NoArvore noAtual;

	public Arvore(NoArvore raiz) {
		this.raiz = raiz;
		this.noAtual = this.raiz;
	}

	public NoArvore getRaiz() {
		return raiz;
	}

	public void setRaiz(NoArvore raiz) {
		this.raiz = raiz;
	}
	
	public NoArvore getNoAtual() {
		return noAtual;
	}

	public boolean adicionarNo(char ultimoMovimento) {
		// cria novo no passando qual foi o ultimo movimento
		// e o no pai é o atual
		NoArvore novoNo = new NoArvore(this.noAtual, ultimoMovimento);
		
		switch (ultimoMovimento) {
		case 'f': // frente
			if (this.noAtual.getFrente() != null)
				return false;
			
			this.noAtual.setFrente(novoNo);
			break;
		case 'e': // esquerda
			if (this.noAtual.getEsquerda() != null)
				return false;
			
			this.noAtual.setEsquerda(novoNo);
			break;
		case 'd': // direita
			if (this.noAtual.getDireita() != null)
				return false;
			
			this.noAtual.setDireita(novoNo);
			break;
		}
		
		this.noAtual = novoNo;
		return true;
	}
	
	public boolean backtrack() {
		if (this.noAtual.getPai() == null)
			return false;
		
		NoArvore noPai = this.noAtual.getPai();
		this.noAtual = noPai;
		return true;
	}
	
	public void buscaProfundidade() {
		Arvore arvore = new Arvore(new NoArvore(null, 'r'));
		buscaProfundidade(arvore, raiz);
	}
	
	//So comecei a fazer. Vou mudar muita coisa disso 
	private Arvore buscaProfundidade(Arvore arvore, NoArvore no) {
		if (no.getFrente() != null) {
			arvore.getNoAtual().setFrente(no.getFrente());
			buscaProfundidade(arvore, no.getFrente());
		}
		if (no.getEsquerda() != null) {
			arvore.getNoAtual().setEsquerda(no.getEsquerda());
			buscaProfundidade(arvore, no.getEsquerda());
		}
		if (no.getDireita() != null) {
			arvore.getNoAtual().setDireita(no.getDireita());
			buscaProfundidade(arvore, no.getDireita());
		}
		return arvore;
	}
}