package org.usfirst.frc.team3786.robot.commands;

import org.usfirst.frc.team3786.robot.subsystems.HuggerSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class HuggerStopCommand extends Command {

	public HuggerStopCommand() {
		// Use requires() here to declare subsystem dependencies
		requires(HuggerSubsystem.getInstance());
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		HuggerSubsystem.getInstance().setBrakeMode(true);
		HuggerSubsystem.getInstance().setMotorSpeed(0);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

}
