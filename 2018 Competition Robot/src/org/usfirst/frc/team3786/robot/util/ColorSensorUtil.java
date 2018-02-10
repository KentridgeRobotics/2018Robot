package org.usfirst.frc.team3786.robot.util;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class ColorSensorUtil {
	
	public double getCurrentHue() {
		NetworkTableInstance ntInst = NetworkTableInstance.getDefault();
		NetworkTable visionTable = ntInst.getTable("vision");
		double value = visionTable.getEntry("Hue").getDouble(0.0);
		return value;
		
	}
	
	public double getCurrentSaturation() {
		NetworkTableInstance ntInst = NetworkTableInstance.getDefault();
		NetworkTable visionTable = ntInst.getTable("vision");
		double value = visionTable.getEntry("Saturation").getDouble(0.0);
		return value;

	}
	
	public double getCurrentLuminosity() {
		NetworkTableInstance ntInst = NetworkTableInstance.getDefault();
		NetworkTable visionTable = ntInst.getTable("vision");
		double value = visionTable.getEntry("Luminosity").getDouble(0.0);
		return value;
	}
	
	public ColorSensorUtil() {
		
		// TODO Color sensor init
	}

	public boolean onBlackTape() {
		
		// TODO Return whether or not on black tape
		return true;
		
	}

}
