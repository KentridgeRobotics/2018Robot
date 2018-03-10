package org.usfirst.frc.team3786.robot.util;

import org.usfirst.frc.team3786.robot.util.BNO055.CalData;
import org.usfirst.frc.team3786.robot.util.BNO055.opmode_t;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GyroUtil implements Runnable {

	private static GyroUtil instance;

	public static GyroUtil getInstance() {
		if (instance == null)
			instance = new GyroUtil();
		return instance;
	}

	private static BNO055 imu;
	private double accelX, accelY;
	private double robotAccelX, robotAccelY, robotHead;
	private double velX, velY, dispX, dispY;
	private double last, now;
	private double dT;
	
	public GyroUtil() {
		imu = BNO055.getInstance(opmode_t.OPERATION_MODE_NDOF);

		

		velX = 0.0;
		velY = 0.0;
		

		dispX = 0.0;
		dispY = 0.0;

		last = System.nanoTime() / 1000000000.0;
	}

	public double getHeadingContinuous() {
		return -imu.getHeadingEuler();
	}

	public double getHeading() {
		return imu.getVectorEuler()[0];
	}

	public double[] getVector() {
		return imu.getVectorEuler();
	}

	public double[] getAccel() {
		double[] result = imu.getVectorLinAccel();
		return result; 
	}

	public double[] getGravity() {
		return imu.getVectorGrav();
	} 
	
	public CalData getCalibration() {
		return imu.getCalibration();
	}

	public void run() {
		now = System.nanoTime() / 1000000000.0;
		dT = now - last;
		double velXNext;
		double velYNext;
		double dispXNext;
		double dispYNext = 0.0;
		robotAccelX = getAccel()[0];// - getGravity()[0];
		robotAccelY = getAccel()[1];// - getGravity()[1];
		

		robotHead = getHeading();
		SmartDashboard.putNumber("Heading", robotHead);
		
		accelX = Math.cos(robotHead) * robotAccelX + Math.sin(robotHead) * robotAccelY;
		accelY = -Math.sin(robotHead) * robotAccelX + Math.cos(robotHead) * robotAccelY;

		velXNext = velX + accelX * dT;
		velYNext = velY + accelY * dT;
		
		velX = velXNext;
		velY = velYNext;
		
		SmartDashboard.putNumber("AccelX", accelX);
		SmartDashboard.putNumber("AccelY", accelY);
		SmartDashboard.putNumber("Time", dT);
		
		SmartDashboard.putNumber("VelX", velX);
		SmartDashboard.putNumber("VelY", velY);
		
		dispXNext = dispX + velX * dT + 0.5 * accelX * Math.pow(dT, 2);

		dispX = dispXNext;
		dispYNext = dispY + velY * dT + 0.5 * accelY * Math.pow(dT, 2);
		dispY = dispYNext;
		
		last = now;

	}
	
	public double getVelX() {
		return velX;
	}

	public double getVelY() {
		return velY;
	}

	public double getDispX() {
		return dispX;
	}

	public double getDispY() {
		return dispY;
	}

}