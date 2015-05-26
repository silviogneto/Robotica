package exec;

import lejos.nxt.ColorSensor;
import lejos.nxt.SensorPort;

public class TesteCor {

	public static void main(String[] args) {
		ColorSensor color = new ColorSensor(SensorPort.S2);
		
		while (true) {
			try {
				Thread.sleep(1000);
				System.out.println("......" + color.getColorID());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
