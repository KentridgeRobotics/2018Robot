package org.usfirst.frc.team3786.robot.util;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LED {
	
	public static void setRGB(int r, int g, int b) {
		double[] data = {r, g, b};
		SmartDashboard.putNumberArray("LED", data);
	}

}