package org.usfirst.frc.team3786.robot.subsystems;

import org.usfirst.frc.team3786.robot.Robot;
import org.usfirst.frc.team3786.robot.commands.MecanumDriveCommand;
import org.usfirst.frc.team3786.robot.util.ExtendedMecanumDrive;
import org.usfirst.frc.team3786.robot.util.GyroUtil;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class WheelsSubsystem extends PIDSubsystem {

	public static WheelsSubsystem instance;

	public static WheelsSubsystem getInstance() {
		if (instance == null)
			instance = new WheelsSubsystem();
		return instance;
	}

	private WPI_TalonSRX leftFront;
	private WPI_TalonSRX leftBack;
	private WPI_TalonSRX rightBack;
	private WPI_TalonSRX rightFront;

	private MecanumDrive mecanumDrive;
	
	private static double kP = 0.1;
	private static double kI = 0.0;
	private static double kD = 0.0;
	private double pidTurnOutput = 0.0;

	public WheelsSubsystem() {
		super("Wheels", kP, kI, kD);
		super.getPIDController().setOutputRange(-1.0, 1.0);
		super.getPIDController().setPercentTolerance(10.0);
		
		leftFront = new WPI_TalonSRX(2); // Yellow
		leftBack = new WPI_TalonSRX(3); // Purple
		rightBack = new WPI_TalonSRX(1); // Blue
		rightFront = new WPI_TalonSRX(4); // Orange
		mecanumDrive = new ExtendedMecanumDrive(leftFront, leftBack, rightFront, rightBack);
				
		leftFront.configOpenloopRamp(0.2, 0);
		leftBack.configOpenloopRamp(0.2, 0);
		rightBack.configOpenloopRamp(0.2, 0);
		rightFront.configOpenloopRamp(0.2, 0);
		
	}

	public void setMotorSpeeds(double leftFrontSpeed, double leftBackSpeed, double rightBackSpeed, double rightFrontSpeed) {
		leftFront.set(leftFrontSpeed);
		leftBack.set(leftBackSpeed);
		rightBack.set(rightBackSpeed);
		rightFront.set(rightFrontSpeed);

	}

	public void setTwoMotorSpeeds(double leftSpeed, double rightSpeed) {
		leftFront.set(leftSpeed);
		leftBack.set(leftSpeed);
		rightBack.set(rightSpeed);
		rightFront.set(rightSpeed);

	}

	public void setBrakeMode(boolean isBraking) {
		NeutralMode mode;
		if (isBraking) {
			mode = NeutralMode.Brake;
		} else {
			mode = NeutralMode.Coast;
		}
		leftFront.setNeutralMode(mode);
		leftBack.setNeutralMode(mode);
		rightBack.setNeutralMode(mode);
		rightFront.setNeutralMode(mode);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
 void setDirectionSpeed(double angle, double speed, double GyroAngle) {
		mecanumDrive.drivePolar(speed, angle, 0);
	}

	public void setXboxDrive(double x, double y, double turn) {
		double heading = GyroUtil.getInstance().getHeading();
		mecanumDrive.driveCartesian(x, y, turn, heading);
	}
	
	public void gyroAssistedDrive(double x, double y) {
		SmartDashboard.putBoolean("Speed Limit", MecanumDriveCommand.inst.getSpeedLimit());
		SmartDashboard.putBoolean("X Disabled", MecanumDriveCommand.inst.getDisableX());
		SmartDashboard.putBoolean("Y Disabled", MecanumDriveCommand.inst.getDisableY());
		mecanumDrive.driveCartesian(x, y, pidTurnOutput, GyroUtil.getInstance().getHeading());
	}

	@Override
	protected double returnPIDInput() {
		return GyroUtil.getInstance().getHeading();
	}

	@Override
	protected void usePIDOutput(double output) {
		this.pidTurnOutput = output;
	}

}
