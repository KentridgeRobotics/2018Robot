package org.usfirst.frc.team3786.robot.util;

public class GyroUtil implements Runnable {
	private static BNO055 gyro;
	private static BNO055 accel;
	private double orientMatrix[][];
	private float accelX, accelY,
				velX, velY,
				dispX, dispY;
	private float last, now;
	
	
;	public GyroUtil() {
		gyro = BNO055.getInstance(BNO055.opmode_t.OPERATION_MODE_IMUPLUS, BNO055.vector_type_t.VECTOR_EULER);
		accel = BNO055.getInstance(BNO055.opmode_t.OPERATION_MODE_IMUPLUS, BNO055.vector_type_t.VECTOR_ACCELEROMETER);
	}
	
	public double getHeading() {
		return gyro.getHeading();
	}
	
	public double[] getAccel() {
		return accel.getVector();
	}

	@Override
	public void run() {
		now = System.currentTimeMillis()*1000;
		accelX = (float) (Math.cos(getHeading())*getAccel()[0] + Math.sin(getHeading())*getAccel()[1]);
		accelY = (float) (-Math.sin(getHeading())*getAccel()[0] + Math.cos(getHeading())*getAccel()[1]);
		if (last != null) {
			
		}
		last = System.currentTimeMillis()*1000;
	}
}