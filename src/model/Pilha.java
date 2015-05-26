package model;

public class Pilha {
	public NoArvore topo;
	public boolean achouFim;
	
	public Pilha() {
		this.topo = null;
		this.achouFim = false;
	}
	
	public boolean isAchouFim() {
		return achouFim;
	}

	public void setAchouFim(boolean achouFim) {
		this.achouFim = achouFim;
	}
	
	public NoArvore getTopo() {
		return topo;
	}
	public void setTopo(NoArvore topo) {
		this.topo = topo;
	}
	
	
	//O no de cima eh "frente"
	//O no de baixo eh "pai"
	public void push(NoArvore no) {
		no.setFrente(null);
		no.setPai(null);
		if (topo == null) {
			topo = no;
		} else {
			topo.setFrente(no);
			no.setPai(topo);
			topo = no;
		}
	}
	
	public NoArvore pop() {
		if (topo == null) {
			return null;
		}
		NoArvore result = topo;
		topo = topo.getPai();
		topo.setFrente(null);
		return result;
	}
	
	
}
