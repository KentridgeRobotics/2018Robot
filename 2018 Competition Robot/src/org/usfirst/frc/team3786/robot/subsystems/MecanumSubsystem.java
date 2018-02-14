package org.usfirst.frc.team3786.robot.subsystems;

import org.usfirst.frc.team3786.robot.OI;
import org.usfirst.frc.team3786.robot.RobotMap;
import org.usfirst.frc.team3786.robot.commands.MecanumDriveCommand;
import org.usfirst.frc.team3786.robot.util.ExtendedMecanumDrive;
import org.usfirst.frc.team3786.robot.util.GyroUtil;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MecanumSubsystem extends PIDSubsystem {

	private WPI_TalonSRX leftFront;
	private WPI_TalonSRX leftBack;
	private WPI_TalonSRX rightBack;
	private WPI_TalonSRX rightFront;

	private ExtendedMecanumDrive mecanumDrive;

	private static double kP = 0.1;
	private static double kI = 0.0;
	private static double kD = 0.0;
	private double pidTurnOutput = 0.0;
	private static double currentHeading = 0.0;

	public MecanumSubsystem() {
		super("Wheels", kP, kI, kD);
		super.getPIDController().setOutputRange(-1.0, 1.0);
		super.getPIDController().setPercentTolerance(10.0);

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

	public void setXboxDrive(double x, double y, double turn) {
		double heading = GyroUtil.getInstance().getHeading();
		mecanumDrive.driveCartesian(x, y, turn, heading);
	}

	public void gyroAssistedDrive(double x, double y, double heading) {
		SmartDashboard.putNumber("Speed Limit", OI.getRightTrigger() * 4);
		SmartDashboard.putBoolean("X Disabled", MecanumDriveCommand.instance.getDisableX());
		SmartDashboard.putBoolean("Y Disabled", MecanumDriveCommand.instance.getDisableY());
		
		setRobotHeading(heading);
		mecanumDrive.driveCartesian(x, y, pidTurnOutput, GyroUtil.getInstance().getHeading());
	}
	
	/**
	 * Update the current heading and feed the value into the PID Controller in charge of turning
	 * @param heading the heading the robot should be at
	 */
	private void setRobotHeading(double heading) {
		currentHeading += heading;
		this.setSetpoint(currentHeading);
	}

	@Override
	protected double returnPIDInput() {
		return GyroUtil.getInstance().getHeading();
	}

	@Override
	protected void usePIDOutput(double output) {
		this.pidTurnOutput = output;
	}
	
	public void initDefaultCommand() {
	}

}
