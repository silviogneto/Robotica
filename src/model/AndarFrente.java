package model;

import lejos.nxt.Motor;
import lejos.robotics.subsumption.Behavior;
import exec.Main;

public class AndarFrente implements Behavior {

	@Override
	public boolean takeControl() {
		return !Main.mapeando && !Main.chegouObjetivo;
	}

	@Override
	public void action() {
		int andar = 940;
		
		Motor.A.rotate(andar, true);
		Motor.B.rotate(andar);
		
		Main.mapeando = true;
	}

	@Override
	public void suppress() {
		Motor.A.stop(true);
		Motor.B.stop(true);
	}
}
