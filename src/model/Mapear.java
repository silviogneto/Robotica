package model;

import lejos.nxt.ColorSensor;
import lejos.nxt.Motor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.subsumption.Behavior;
import exec.Main;

public class Mapear implements Behavior {

	// variaveis da movimentacao
	private int anguloPositivo = 280;
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
		if (!podeFrente()) {
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
			}
		}
	}

	@Override
	public void suppress() {
	}
	
	private boolean podeEsquerda() {
		try {
			Motor.C.rotate(90); // gira sonar para esquerda
			return podeFrente();			
		} finally {
			Motor.C.rotate(-90); // volta sonar para frente	
		}
	}
	
	private void virarEsquerda() {
		Motor.A.rotate(anguloPositivo, true);
		Motor.B.rotate(anguloNegativo);	
	}
	
	private boolean podeDireita() {
		try {
			Motor.C.rotate(-90); // gira sonar para direita
			return podeFrente(20);	
		} finally {
			Motor.C.rotate(90); // volta sonar para frente			
		}
	}
	
	private void virarDireita() {
		Motor.A.rotate(anguloNegativo, true);
		Motor.B.rotate(anguloPositivo);
	}
	
	private boolean podeFrente() {
		return podeFrente(15);
	}
	
	private boolean podeFrente(int distancia) {
		return (sonic.getDistance() > distancia);
	}
	
	private void andarTras() {
		int andar = -750;
		
		Motor.A.rotate(andar, true);
		Motor.B.rotate(andar);
	}
}
