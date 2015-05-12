package model;

import exec.Main;
import lejos.nxt.ColorSensor;
import lejos.nxt.Motor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.subsumption.Behavior;

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
		// sonar olhando pra frente
		if (sonic.getDistance() > 15) {
			
		} else {
			Motor.C.rotate(90);
			
//			if ()
		}
		
		Main.mapeando = false;
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub

	}

}
