package org.usfirst.frc.team3786.robot.subsystems;

import org.usfirst.frc.team3786.robot.RobotMap;
import org.usfirst.frc.team3786.robot.commands.MecanumDriveCommand;
import org.usfirst.frc.team3786.robot.util.ExtendedMecanumDrive;
import org.usfirst.frc.team3786.robot.util.GyroUtil;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MecanumSubsystem extends Subsystem {

	private WPI_TalonSRX leftFront;
	private WPI_TalonSRX leftBack;
	private WPI_TalonSRX rightBack;
	private WPI_TalonSRX rightFront;

	private ExtendedMecanumDrive mecanumDrive;

	public MecanumSubsystem() {
		leftFront = new WPI_TalonSRX(RobotMap.frontLeftMotor);
		leftBack = new WPI_TalonSRX(RobotMap.backLeftMotor);
		rightBack = new WPI_TalonSRX(RobotMap.backRightMotor);
		rightFront = new WPI_TalonSRX(RobotMap.frontRightMotor);

		leftFront.configOpenloopRamp(0.2, 0);
		leftBack.configOpenloopRamp(0.2, 0);
		rightFront.configOpenloopRamp(0.2, 0);
		rightBack.configOpenloopRamp(0.2, 0);
		
		mecanumDrive = new ExtendedMecanumDrive(leftFront, leftBack, rightFront, rightBack);
	}

	public void setMotorSpeeds(double leftFrontSpeed, double leftBackSpeed, double rightBackSpeed,
			double rightFrontSpeed) {
		leftFront.set(leftFrontSpeed);
		leftBack.set(leftBackSpeed);
		rightBack.set(rightBackSpeed);
		rightFront.set(rightFrontSpeed);
	}

	public void setBrakeMode(boolean isBraking) {
		NeutralMode mode;
		if (isBraking)
			mode = NeutralMode.Brake;
		else
			mode = NeutralMode.Coast;
		leftFront.setNeutralMode(mode);
		leftBack.setNeutralMode(mode);
		rightBack.setNeutralMode(mode);
		rightFront.setNeutralMode(mode);
	}

	public void setDirectionSpeed(double angle, double speed, double GyroAngle) {
		mecanumDrive.drivePolar(speed, angle, 0, GyroAngle);
	}


	public void gyroAssistedDrive(double x, double y, double heading) {
		SmartDashboard.putBoolean("X Disabled", MecanumDriveCommand.instance.getDisableX());
		SmartDashboard.putBoolean("Y Disabled", MecanumDriveCommand.instance.getDisableY());
		mecanumDrive.driveCartesian(x, y, heading);
	}
	
	public void initDefaultCommand() {
	}

}
