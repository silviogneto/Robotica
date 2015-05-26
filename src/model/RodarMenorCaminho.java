package model;

import exec.Main;
import lejos.robotics.subsumption.Behavior;

public class RodarMenorCaminho implements Behavior {

	@Override
	public boolean takeControl() {
		return Main.rodasMenorCaminho;
	}

	@Override
	public void action() {
		// TODO buscar na arvore o caminho que deve fazer

		
	}

	@Override
	public void suppress() {
	}
}