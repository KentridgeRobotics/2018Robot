package org.usfirst.frc.team3786.robot.commands;

import org.usfirst.frc.team3786.robot.subsystems.LifterSubsystem;
import edu.wpi.first.wpilibj.command.Command;

public class LifterStopCommand extends Command{
	
	public LifterStopCommand() {
		// Use requires() here to declare subsystem dependencies
		requires(LifterSubsystem.getInstance());
	}

	
	// Called just before this Command runs the first time
		@Override
		protected void initialize() {
			LifterSubsystem.getInstance().setBrakeMode(true);
			LifterSubsystem.getInstance().setLiftSpeed(0.0);
		}

		// Called repeatedly when this Command is scheduled to run
		@Override
		protected void execute() {
		}

		// Make this return true when this Command no longer needs to run execute()
		@Override
		protected boolean isFinished() {
			return false;
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
		



