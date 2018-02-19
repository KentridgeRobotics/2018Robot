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

		last = 0.0;
		lastCheckNanos = System.nanoTime();
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
		return imu.getVectorAccel();
	}

	public CalData getCalibration() {
		return imu.getCalibration();
	}

	public void runold() {
		now = System.currentTimeMillis() * 1000;
		dT = now - last;

		robotAccelX = getAccel()[0];
		robotAccelY = getAccel()[1];
		
		SmartDashboard.putNumber("AccelX", accelX);
		SmartDashboard.putNumber("AccelY", accelY);
		
		robotHead = getHeading();
		SmartDashboard.putNumber("Heading", robotHead);
		
		accelX = (float) (Math.cos(robotHead) * robotAccelX + Math.sin(robotHead) * robotAccelY);
		accelY = (float) (-Math.sin(robotHead) * robotAccelX + Math.cos(robotHead) * robotAccelY);

		velX[1] = velX[0] + accelX * dT;
		velY[1] = velY[0] + accelY * dT;

		velX[0] = velX[1];
		velY[0] = velY[1];

		dispX[1] = dispX[0] + velX[1] * dT + 0.5 * accelX * Math.pow(dT, 2);
		dispY[1] = dispY[0] + velY[1] * dT + 0.5 * accelY * Math.pow(dT, 2);

		dispX[0] = dispX[1];
		dispY[0] = dispY[1];

		last = now;

	}

	long lastCheckNanos;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		// What is our position now?
		// We need to know:
		// - previous position (x and y)
		// - previous velocity (dx and dy)
		// - new veolocity (dxNext and dyNext)
		// Our new position is :
		// xNew = x + dt * (dx + dxNext)/2.0
		long now = System.nanoTime();
		dx_next = x + dT * (dx + dx_next) / 2.0;
		dy_next = y + dT * (dy + dy_next) / 2.0;
		
		dx = dx_next;
		dy = dy_next;		
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