package org.usfirst.frc.team3786.robot.subsystems;

import org.usfirst.frc.team3786.robot.RobotMap;
import org.usfirst.frc.team3786.robot.commands.MecanumDriveCommand;
import org.usfirst.frc.team3786.robot.util.ExtendedMecanumDrive;
import org.usfirst.frc.team3786.robot.util.GyroUtil;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MecanumSubsystem extends PIDSubsystem implements ChargersDriveSubsystem {

	private WPI_TalonSRX leftFront;
	private WPI_TalonSRX leftBack;
	private WPI_TalonSRX rightBack;
	private WPI_TalonSRX rightFront;

	private ExtendedMecanumDrive mecanumDrive;
	
	private static boolean usePID = false;

	// TODO Re-Tune these values when the robot is put together
	private static double kP = 0.1;
	private static double kI = 0.0;
	private static double kD = 0.0;
	private double pidTurnOutput = 0.0;

	public MecanumSubsystem() {
		super("Wheels", kP, kI, kD);
		super.getPIDController().setOutputRange(-1.0, 1.0);
		super.getPIDController().setPercentTolerance(10.0);
		if (usePID) {
			super.enable();
		}

		leftFront = new WPI_TalonSRX(RobotMap.frontLeftMotor);
		leftBack = new WPI_TalonSRX(RobotMap.backLeftMotor);
		rightBack = new WPI_TalonSRX(RobotMap.backRightMotor);
		rightFront = new WPI_TalonSRX(RobotMap.frontRightMotor);

		//leftFront.configOpenloopRamp(0.2, 0);
		//leftBack.configOpenloopRamp(0.2, 0);
		//rightFront.configOpenloopRamp(0.2, 0);
		//rightBack.configOpenloopRamp(0.2, 0);

		mecanumDrive = new ExtendedMecanumDrive(leftFront, leftBack, rightFront, rightBack);
		
		setBrakeMode(true);
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

	public void setDirectionSpeed(double angle, double speed) {
		mecanumDrive.drivePolar(speed, angle, 0, GyroUtil.getInstance().getHeading());
	}
	
	/* (non-Javadoc)
	 * @see org.usfirst.frc.team3786.robot.subsystems.ChargersDriveSubsystem#gyroAssistedDrive(double, double, double)
	 */
	@Override
	public void gyroAssistedDrive(double x, double y, double turnRate) {
		SmartDashboard.putBoolean("X Disabled", MecanumDriveCommand.getInstance().getDisableX());
		SmartDashboard.putBoolean("Y Disabled", MecanumDriveCommand.getInstance().getDisableY());
		if (usePID) {
			//setRobotHeading(GyroUtil.getInstance().getHeadingContinuous()+turnRate);
			gyroAssistedTurn(turnRate);
			mecanumDrive.driveCartesian(x, y, pidTurnOutput, GyroUtil.getInstance().getHeading());
		} else
			mecanumDrive.driveCartesian(x, y, turnRate);
	}
	
	public void gyroAssistedTurn(double turn) {
		this.setSetpointRelative(turn);
	}

	/**
	 * Turn the robot to a specific angle 
	 * @param angle the angle to turn the robot to.
	 */
	private void setRobotHeading(double angle) {
		// This finds the shortest way to get to the desired angle
		this.setSetpointRelative(Math.IEEEremainder(angle-GyroUtil.getInstance().getHeading(), 360.0));
	}

	@Override
	protected double returnPIDInput() {
		return GyroUtil.getInstance().getHeadingContinuous();
	}

	@Override
	protected void usePIDOutput(double output) {
		SmartDashboard.putNumber("PID output", output);
		
		this.pidTurnOutput = output;
	}

	public void initDefaultCommand() {
	}

}
