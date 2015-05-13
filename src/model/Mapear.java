package model;

import lejos.nxt.ColorSensor;
import lejos.nxt.Motor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.subsumption.Behavior;
import exec.Main;

public class Mapear implements Behavior {

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
		int anguloPositivo = 280;
		int anguloNegativo = anguloPositivo * -1;
		
		// sonar olhando pra frente
		if (!podeFrente()) {
			if (podeEsquerda()) {
				Motor.A.rotate(anguloPositivo, true);
				Motor.B.rotate(anguloNegativo);
			} else if (podeDireita()) {
				Motor.A.rotate(anguloNegativo, true);
				Motor.B.rotate(anguloPositivo);
			}
		}
		
		Main.mapeando = false;
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub

	}
	
	private boolean podeEsquerda() {
		try {
			Motor.C.rotate(90); // gira sonar para esquerda
			return podeFrente();			
		} finally {
			Motor.C.rotate(-90); // volta sonar para frente	
		}
	}
	
	private boolean podeDireita() {
		try {
			Motor.C.rotate(-90); // gira sonar para direita
			return podeFrente(20);	
		} finally {
			Motor.C.rotate(90); // volta sonar para frente			
		}
	}
	
	private boolean podeFrente() {
		return podeFrente(15);
	}
	
	private boolean podeFrente(int distancia) {
		return (sonic.getDistance() > distancia);
	}
}
