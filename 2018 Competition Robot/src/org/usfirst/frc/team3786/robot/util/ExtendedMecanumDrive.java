package org.usfirst.frc.team3786.robot.util;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.hal.HAL;
import edu.wpi.first.wpilibj.hal.FRCNetComm.tInstances;
import edu.wpi.first.wpilibj.hal.FRCNetComm.tResourceType;

public class ExtendedMecanumDrive extends MecanumDrive {

	public ExtendedMecanumDrive(SpeedController frontLeftMotor, SpeedController rearLeftMotor,
			SpeedController frontRightMotor, SpeedController rearRightMotor) {
		super(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
		// TODO Auto-generated constructor stub
	}
	 /**
	   * Drive method for Mecanum platform.
	   *
	   * <p>Angles are measured counter-clockwise from straight ahead. The speed at which the robot
	   * drives (translation) is independent from its angle or rotation rate.
	   *
	   * @param magnitude The robot's speed at a given angle [-1.0..1.0]. Forward is positive.
	   * @param angle     The angle around the Z axis at which the robot drives in degrees [-180..180].
	   * @param zRotation The robot's rotation rate around the Z axis [-1.0..1.0]. Clockwise is
	   *                  positive.
	   */
	  @SuppressWarnings("ParameterName")
	  public void drivePolar(double magnitude, double angle, double zRotation, double GyroAngle) {
	    driveCartesian(magnitude * Math.sin(angle * (Math.PI / 180.0)),
                magnitude * Math.cos(angle * (Math.PI / 180.0)), zRotation, 0.0);
} 
	}
