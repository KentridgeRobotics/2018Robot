package org.usfirst.frc.team3786.robot.commands;

import org.usfirst.frc.team3786.robot.Robot;
import org.usfirst.frc.team3786.robot.subsystems.WheelsSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Robot Drives forward until it passes black line.
 * Basic Code to working with 
 */


public class AutonomousCrossTheLine extends Command {
	private int StartingPosition;
	
    public AutonomousCrossTheLine(int StartingPosition) {
    	this.StartingPosition = StartingPosition;
    	requires(WheelsSubsystem.getInstance());
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
   
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(StartingPosition == 1) {
    		//drive at 335 degrees
    		//WheelsSubsystem.getInstance().setDirectionSpeed(335, 1.0, 0);
    	} else if(StartingPosition == 2) {
    		//???
    	} else if(StartingPosition == 3) {
    		//drive at 25 degrees
    		//WheelsSubsystem.getInstance().setDirectionSpeed(25, 1.0, 0);
    	}
    	
    	//WheelsSubsystem.getInstance().setDirectionSpeed(0, 1.0, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Robot.instance.colorSenseUtil.onBlackTape()) {
    		return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//WheelsSubsystem.getInstance().setDirectionSpeed(0, 0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    }
}
