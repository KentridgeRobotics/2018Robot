package org.usfirst.frc.team3786.robot.util;

import edu.wpi.first.wpilibj.AnalogInput;

public class UltraSonicDistance {
	private static UltraSonicDistance instance; 
	private AnalogInput analogInput = new AnalogInput(0);
	 
	public static UltraSonicDistance getInstance() {
		if (instance == null)
			instance = new UltraSonicDistance();
		return instance;
	}
	public double getVoltage() {
		return analogInput.getVoltage();
	}
	public double getDistance() {
		return analogInput.getVoltage()/0.0098;
	}
}
