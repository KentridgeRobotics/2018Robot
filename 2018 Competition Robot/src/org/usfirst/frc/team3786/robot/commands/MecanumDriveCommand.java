package org.usfirst.frc.team3786.robot.commands;

import org.usfirst.frc.team3786.robot.OI;
import org.usfirst.frc.team3786.robot.Robot;
import org.usfirst.frc.team3786.robot.subsystems.WheelsSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class MecanumDriveCommand extends Command {

	public static MecanumDriveCommand inst;

	private boolean halfSpeed = false;

	public static MecanumDriveCommand getInstance() {
		if (inst == null)
			inst = new MecanumDriveCommand();
		return inst;
	}

	public MecanumDriveCommand() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(WheelsSubsystem.getInstance());
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		WheelsSubsystem.getInstance();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// When the number is negative, the wheels go forwards.
		// When the number is positive, the wheels go backwards.
		double x = OI.getLeftStickX();
		double y = OI.getLeftStickY();
		if (this.halfSpeed) {
			x = x / 3;
			y = y / 3;
		}
		Robot.wheelsSubsystem.setXboxDrive(x, -y);

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
	
	public void setHalfSpeed(boolean halfSpeed) {
		this.halfSpeed = halfSpeed;
	}
	
	public void toggleHalfSpeed() {
		if (this.halfSpeed)
			this.halfSpeed = false;
		else
			this.halfSpeed = true;
	}
}
