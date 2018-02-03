package org.usfirst.frc.team3786.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class HalfSpeedCommand extends Command {

	public HalfSpeedCommand() {
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		MecanumDriveCommand.inst.toggleHalfSpeed();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
	
