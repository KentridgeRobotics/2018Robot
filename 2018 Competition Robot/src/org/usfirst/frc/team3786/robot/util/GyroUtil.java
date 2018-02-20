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
	private double[] velX, velY, dispX, dispY;
	private double last, now;
	private double dT;

	public GyroUtil() {
		imu = BNO055.getInstance(opmode_t.OPERATION_MODE_NDOF);

		velX = new double[2];
		velY = new double[2];

		velX[0] = 0.0;
		velY[0] = 0.0;

		dispX = new double[2];
		dispY = new double[2];

		dispX[0] = 0.0;
		dispY[0] = 0.0;

		last = System.currentTimeMillis() / 1000.0;
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
		SmartDashboard.putNumber("AccelIMU", imu.getHeadingAccel());
		return imu.getVectorAccel();
	}

	public double[] getGravity() {
		return imu.getVectorGrav();
	}
	
	public CalData getCalibration() {
		return imu.getCalibration();
	}

	public void run() {
		now = System.currentTimeMillis() / 1000.0;
		dT = now - last;

		robotAccelX = getAccel()[0] - getGravity()[0];
		robotAccelY = getAccel()[1] - getGravity()[1];
		

		robotHead = getHeading();
		SmartDashboard.putNumber("Heading", robotHead);
		
		accelX = Math.cos(robotHead) * robotAccelX + Math.sin(robotHead) * robotAccelY;
		accelY = -Math.sin(robotHead) * robotAccelX + Math.cos(robotHead) * robotAccelY;

		velX[1] = velX[0] + accelX * dT;
		velY[1] = velY[0] + accelY * dT;
		
		velX[0] = velX[1];
		velY[0] = velY[1];
		
		SmartDashboard.putNumber("AccelX", accelX);
		SmartDashboard.putNumber("AccelY", accelY);
		SmartDashboard.putNumber("Time", dT);
		
		SmartDashboard.putNumberArray("VelX", velX);
		SmartDashboard.putNumberArray("VelY", velY);
		
		dispX[1] = dispX[0] + velX[1] * dT + 0.5 * accelX * Math.pow(dT, 2);
		dispY[1] = dispY[0] + velY[1] * dT + 0.5 * accelY * Math.pow(dT, 2);

		dispX[0] = dispX[1];
		dispY[0] = dispY[1];

		last = now;

	}

	
	// Current value on x
	private double x;
	
	// Current value on y
	private double y;
	
	// Current velocity on x
	private double dx;
	
	// Current velocity on y
	private double dy;
	
	// New value from dx
	private double dx_next;
	
	// New value from dy
	private double dy_next;
	
	public double getVelX() {
		return velX[1];
	}

	public double getVelY() {
		return velY[1];
	}

	public double getDispX() {
		return dispX[1];
	}

	public double getDispY() {
		return dispY[1];
	}

}