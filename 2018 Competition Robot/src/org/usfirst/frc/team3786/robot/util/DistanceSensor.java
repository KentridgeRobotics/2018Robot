package org.usfirst.frc.team3786.robot.util;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;


public class DistanceSensor {
	private NetworkTable DistanceTable;
	
	public double getX() {
		double value = DistanceTable.getEntry("x").getDouble(0.0);
		return value;
	}
	
	public double getY() {
		double value = DistanceTable.getEntry("y").getDouble(0.0);
		return value;
	}
	public double getR() {
		double value = DistanceTable.getEntry("r").getDouble(0.0);
		return value;
	}
	
	// assume return Radians from Switch
	public double getRadians() {
		return DistanceTable.getEntry("dist").getDouble(0.0);
	}
	
	// assume return Distance from Switch 
	public double getDistance() {
		return DistanceTable.getEntry("rads").getDouble(0.0);
	}
	
	public DistanceSensor() {
		NetworkTableInstance ntInst = NetworkTableInstance.getDefault();
		DistanceTable = ntInst.getTable("Distance");
		
	}
	public double GetDistanceFromObstacleInInches() {
		double value = DistanceTable.getEntry("Distance").getDouble(0.0);
		return value;
	}
	
	

	
	
}
