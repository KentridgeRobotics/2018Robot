/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3786.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3786.robot.Robot;
import org.usfirst.frc.team3786.robot.subsystems.TwoWheelSubsystem;
import org.usfirst.frc.team3786.robot.OI;

/**
 * An example command.  You can replace me with your own command.
 */
public class switchdrive extends Command {
	
	private static int switchDrive = 0;
	
	public switchdrive() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.TwoWheelSubsystem);
		requires(Robot.WheelsSubsystem);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		double x = OI.getLeftStickX();
		double y = OI.getLeftStickY();
		double r = Math.hypot(x, y);
		double theta = Math.atan2(y,x);
		
		System.out.println("r = " + r +"theta = " + theta);
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
