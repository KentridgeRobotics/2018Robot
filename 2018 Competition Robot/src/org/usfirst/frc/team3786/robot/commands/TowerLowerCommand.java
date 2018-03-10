package org.usfirst.frc.team3786.robot.commands;

import org.usfirst.frc.team3786.robot.subsystems.TowerSubsystem;
import edu.wpi.first.wpilibj.command.Command;

public class TowerLowerCommand extends Command {

	public TowerLowerCommand() {
		// Use requires() here to declare subsystem dependencies
		requires(TowerSubsystem.getInstance());
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
<<<<<<< HEAD
		TowerSubsystem.getInstance().setMotorSpeed(-0.5);
=======
		TowerSubsystem.getInstance().setMotorSpeed(-0.7);
>>>>>>> branch 'master' of https://github.com/KentridgeRobotics/2018Robot.git
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return true;
	}

}
