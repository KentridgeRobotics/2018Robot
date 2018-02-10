package org.usfirst.frc.team3786.robot.commands;

import org.usfirst.frc.team3786.robot.OI;
import org.usfirst.frc.team3786.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MecanumDriveCommand extends Command {
	public static MecanumDriveCommand instance;

	private boolean xDisable = false;
	private boolean yDisable = false;

	public static MecanumDriveCommand getInstance() {
		if (instance == null)
			instance = new MecanumDriveCommand();
		return instance;
	}

	public MecanumDriveCommand() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.instance.getMecanumSubsystem());
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.instance.getMecanumSubsystem().setSetpoint(0.0);
		if (!Robot.instance.getMecanumSubsystem().getPIDController().isEnabled())
			Robot.instance.getMecanumSubsystem().enable();

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// Drive controls
		double x = OI.getLeftStickX();
		double y = OI.getLeftStickY();
		// Turning controls
		double turn = OI.getRightStickX();
		double limit = OI.getLeftTrigger();
		x = x / (limit * 4);
		y = y / (limit * 4);
		turn *= (limit * 4); // The number is the "turn gain"
		if (this.xDisable)
			x = 0;
		if (this.yDisable)
			y = 0;

		SmartDashboard.putBoolean("PIDTurn", Robot.instance.getMecanumSubsystem().getPIDController().isEnabled());
		SmartDashboard.putNumber("PID Setpoint", Robot.instance.getMecanumSubsystem().getSetpoint());
		SmartDashboard.putNumber("Turn", turn);

		// Update motors with controls
		Robot.instance.getMecanumSubsystem().gyroAssistedDrive(x, -y, turn);

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}

	public boolean setDisableX(boolean disableX) {
		this.xDisable = disableX;
		return this.xDisable;
	}

	public boolean setDisableY(boolean disableY) {
		this.yDisable = disableY;
		return this.yDisable;
	}

	public boolean getDisableX() {
		return this.xDisable;
	}

	public boolean getDisableY() {
		return this.yDisable;
	}

	public void toggleX() {
		if (this.xDisable)
			this.xDisable = false;
		else
			this.xDisable = true;
	}

	public void toggleY() {
		if (this.yDisable)
			this.yDisable = false;
		else
			this.yDisable = true;
	}
}
