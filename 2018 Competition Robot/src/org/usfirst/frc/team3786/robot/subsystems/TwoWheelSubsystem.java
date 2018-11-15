/*
 * 
 */
package org.usfirst.frc.team3786.robot.subsystems;

import org.usfirst.frc.team3786.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TwoWheelSubsystem extends Subsystem implements ChargersDriveSubsystem {

	private Spark left;
	private Spark right;

	private DifferentialDrive differentialDrive;
	
	public TwoWheelSubsystem() {
		left = new Spark(0);
		right = new Spark(1);
		
		differentialDrive = new DifferentialDrive(left, right);
	}

	public void setMotorSpeeds(double leftSpeed, double rightSpeed) {
		left.set(leftSpeed);
		right.set(rightSpeed);
	}


	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	@Override
	public void gyroAssistedDrive(double x, double y, double turnRate) {
		differentialDrive.arcadeDrive(y, turnRate * 0.75);
		SmartDashboard.putNumber("Gyro assisted drive x", x);
		SmartDashboard.putNumber("Gyro assisted drive y", y);
		SmartDashboard.putNumber("Gyro assisted drive turn rate", turnRate);

	}
}
