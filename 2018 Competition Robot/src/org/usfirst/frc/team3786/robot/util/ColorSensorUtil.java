package org.usfirst.frc.team3786.robot.util;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class ColorSensorUtil {
	private NetworkTable visionTable;
	
	public double getCurrentHue() {
		double value = visionTable.getEntry("Hue").getDouble(0.0);
		return value;
		
	}
	
	public double getCurrentSaturation() {
		double value = visionTable.getEntry("Saturation").getDouble(0.0);
		return value;

	}
	
	public double getCurrentLuminosity() {
		double value = visionTable.getEntry("Luminosity").getDouble(0.0);
		return value;
	}
	
	public ColorSensorUtil() {
		NetworkTableInstance ntInst = NetworkTableInstance.getDefault();
		visionTable = ntInst.getTable("vision");
		
	}

	public boolean onBlackTape() {
		
		// TODO Return whether or not on black tape
		return true;
		
	}

}
