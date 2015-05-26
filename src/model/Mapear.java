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
	private int corVermelho = 0;
	private int corVerde = 1;

	private UltrasonicSensor sonic;
	private ColorSensor color;

	public Mapear(UltrasonicSensor sonic, ColorSensor color) {
		this.sonic = sonic;
		this.color = color;
	}

	@Override
	public boolean takeControl() {
		return Main.mapeando; // && !Main.chegouObjetivo;
	}

	@Override
	public void action() {
		boolean conseguiuMovimentar = false;

		// verifica cor do piso
		if (color.getColorID() == corVerde) {
			Main.chegouObjetivo = true;
			Main.arvore.getNoAtual().setUltimoMovimento('z');
		}
		
		if (color.getColorID() == corVermelho && Main.chegouObjetivo) {
			Main.rodasMenorCaminho = true;
			Main.mapeando = false;
			return;
		}
		
		//se nao chegou no objetivo entao continua validando
		if (!Main.chegouObjetivo) {
			// sonar olhando pra frente
			if (!podeFrente('f')) {
				if (podeEsquerda()) {
					conseguiuMovimentar = Main.arvore.adicionarNo('e');

					if (conseguiuMovimentar) {
						virarEsquerda();
					}
				} else if (podeDireita()) {
					conseguiuMovimentar = Main.arvore.adicionarNo('d');

					if (conseguiuMovimentar) {
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
		}
		
		if (conseguiuMovimentar && !Main.chegouObjetivo) {
			// validar a cor do piso pra ver se ja chegou no destino
			
//			(Main.arvore.getNoAtual().getUltimoMovimento() != 'r')
//			&& (color.getColorID() != 1)
//			&& ((direcao != 'f') || (Main.arvore.getNoAtual().getFrente() == null))
//			&& ;

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
//			} else {
				// talvez aqui tenha que percorrer todo o mapa no menor caminho
				// Main.arvore.getNoAtual().setUltimoMovimento('z'); //Chegou ao
				// destino
				// Main.chegouObjetivo = true;
				// Main.mapeando = false;
			}
		}
	}

	@Override
	public void suppress() {
	}

	private boolean podeEsquerda() {
		try {
			Motor.C.rotate(90); // gira sonar para esquerda
			return podeFrente('e');
		} finally {
			Motor.C.rotate(-90); // volta sonar para frente
		}
	}

	private void virarEsquerda() {
		Motor.A.rotate(anguloNegativo, true);
		Motor.B.rotate(anguloPositivo);
	}

	private boolean podeDireita() {
		try {
			Motor.C.rotate(-90); // gira sonar para direita
			return podeFrente('d', 25);
		} finally {
			Motor.C.rotate(90); // volta sonar para frente
		}
	}

	private void virarDireita() {
		Motor.A.rotate(anguloPositivo, true);
		Motor.B.rotate(anguloNegativo);
	}

	private boolean podeFrente(char direcao) {
		return podeFrente(direcao, 15);
	}
	
	private boolean podeFrente(char direcao, int distancia) {
		return (sonic.getDistance() > distancia);
	}
	
	private void andarTras() {
		int andar = -800;

		Motor.A.rotate(andar, true);
		Motor.B.rotate(andar);
	}
}
