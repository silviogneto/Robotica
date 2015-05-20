package model;

import lejos.nxt.ColorSensor;
import lejos.nxt.Motor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.subsumption.Behavior;
import exec.Main;

public class Mapear implements Behavior {

	// variaveis da movimentacao
	private int anguloPositivo = -280;
	private int anguloNegativo = anguloPositivo * -1;
	
	private UltrasonicSensor sonic;
	private ColorSensor color;
	
	public Mapear(UltrasonicSensor sonic, ColorSensor color) {
		this.sonic = sonic;
		this.color = color;
	}
	
	@Override
	public boolean takeControl() {
		return Main.mapeando && !Main.chegouObjetivo;
	}

	@Override
	public void action() {
		
		boolean conseguiuMovimentar;
		
		// sonar olhando pra frente
		if (!podeFrente('f')) {
			if (podeEsquerda()) {
				if ((conseguiuMovimentar = Main.arvore.adicionarNo('e'))) {
					virarEsquerda();
				}
			} else if (podeDireita()) {
				if ((conseguiuMovimentar = Main.arvore.adicionarNo('d'))) {
					virarDireita();
				}
			} else {
				// se cair aqui tem que ir pra tras
				conseguiuMovimentar = false;
			}
		} else {
			// aqui esta indo pra frente
			conseguiuMovimentar = Main.arvore.adicionarNo('f');
		}
		
		if (conseguiuMovimentar) {
			// validar a cor do piso pra ver se ja chegou no destino
			
			Main.mapeando = false;	
		} else {
			// se nao conseguiu movimentar tem que ir pra tras
			char ultimoMovimento = Main.arvore.getNoAtual().getUltimoMovimento();
			if (Main.arvore.backtrack()) {
				// se fez o backtracking entao nao esta na raiz
				
				//ultimoMovimento = Main.arvore.getNoAtual().getUltimoMovimento(); //aaaaaaaaaa
				
				andarTras();
				switch (ultimoMovimento) {
				case 'e':
					virarDireita();
					break;
				case 'd':
					virarEsquerda();
					break;
				}	
			} else {
				// talvez aqui tenha que percorrer todo o mapa no menor caminho
				Main.arvore.getNoAtual().setUltimoMovimento('z'); //Chegou ao destino
				Main.chegouObjetivo = true;
				Main.mapeando = false;
			}
		}
	}

	@Override
	public void suppress() {
	}
	
	private boolean podeEsquerda() {
		if (Main.arvore.getNoAtual().getEsquerda() != null) {
			return false;
		}
		try {
			Motor.C.rotate(90); // gira sonar para esquerda
			return podeFrente('e');			
		} finally {
			Motor.C.rotate(-90); // volta sonar para frente	
		}
	}
	
	private void virarEsquerda() {
		Motor.A.rotate(anguloPositivo, true);
		Motor.B.rotate(anguloNegativo);	
	}
	
	private boolean podeDireita() {
		if (Main.arvore.getNoAtual().getDireita() != null)
			return false;
		try {
			Motor.C.rotate(-90); // gira sonar para direita
			return podeFrente('d', 25);	
		} finally {
			Motor.C.rotate(90); // volta sonar para frente			
		}
	}
	
	private void virarDireita() {
		Motor.A.rotate(anguloNegativo, true);
		Motor.B.rotate(anguloPositivo);
	}
	
	private boolean podeFrente(char direcao) {
		return podeFrente(direcao, 15);
	}
	
	private boolean podeFrente(char direcao, int distancia) {
		return  (Main.arvore.getNoAtual().getUltimoMovimento() != 'r')
				&& (color.getColorID() != 1)
				&& ((direcao != 'f') || (Main.arvore.getNoAtual().getFrente() == null)) 
				&& (sonic.getDistance() > distancia);
	}
	
	private void andarTras() {
		int andar = -940;
		
		Motor.A.rotate(andar, true);
		Motor.B.rotate(andar);
	}
}
