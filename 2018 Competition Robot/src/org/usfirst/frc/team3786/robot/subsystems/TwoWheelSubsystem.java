/*
 * 
 */
package org.usfirst.frc.team3786.robot.subsystems;

import org.usfirst.frc.team3786.robot.RobotMap;
import org.usfirst.frc.team3786.robot.subsystems.MecanumSubsystem;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class TwoWheelSubsystem extends Subsystem implements ChargersDriveSubsystem {

	private WPI_TalonSRX left;
	private WPI_TalonSRX right;

	public TwoWheelSubsystem() {
		left = new WPI_TalonSRX(RobotMap.leftMotor);
		right = new WPI_TalonSRX(RobotMap.rightMotor);

		left.configOpenloopRamp(0.3, 0);
		right.configOpenloopRamp(0.3, 0);
	}

	public void setMotorSpeeds(double leftSpeed, double rightSpeed) {
		left.set(leftSpeed);
		right.set(rightSpeed);
	}

	public void setBrakeMode(boolean isBraking) {
		NeutralMode mode;
		if (isBraking)
			mode = NeutralMode.Brake;
		else
			mode = NeutralMode.Coast;
		left.setNeutralMode(mode);
		right.setNeutralMode(mode);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	@Override
	public void gyroAssistedDrive(double x, double y, double turnRate) {
		// TODO Auto-generated method stub
		
	}

}
