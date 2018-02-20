package org.usfirst.frc.team3786.robot.commands;

import org.usfirst.frc.team3786.robot.OI;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Command;

public class DEBUGCOMMAND extends Command {
	private static DEBUGCOMMAND instance;

	private WPI_TalonSRX talon = new WPI_TalonSRX(5);

	public static DEBUGCOMMAND getInstance() {
		if (instance == null)
			instance = new DEBUGCOMMAND();
		return instance;
	}

	public DEBUGCOMMAND() {
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		System.out.println(OI.getRightStickY());
		talon.set(OI.getRightStickY());
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
}
