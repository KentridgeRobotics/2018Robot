package org.usfirst.frc.team3786.robot.util;

import org.usfirst.frc.team3786.robot.util.BNO055.CalData;
import org.usfirst.frc.team3786.robot.util.BNO055.opmode_t;

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
	}

	public double getHeadingContinuous() {
		return -imu.getHeadingEuler();
	}

	public double getHeading() {
		return 360 - imu.getVectorEuler()[0];
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

	@Override
	public void run() {
		now = System.currentTimeMillis() * 1000;
		dT = now - last;

		robotAccelX = getAccel()[0];
		robotAccelY = getAccel()[1];
		robotHead = getHeading();

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