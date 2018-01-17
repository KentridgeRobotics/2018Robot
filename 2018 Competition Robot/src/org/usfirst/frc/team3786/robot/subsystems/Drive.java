package org.usfirst.frc.team3786.robot.subsystems;

public interface Drive {

	void setMotorSpeeds(double leftFrontSpeed, double leftBackSpeed, double rightBackSpeed, double rightFrontSpeed);

	void setBrakeMode(boolean isBraking);

	void setTwoMotorSpeeds(double leftSpeed, double rightSpeed);

}
