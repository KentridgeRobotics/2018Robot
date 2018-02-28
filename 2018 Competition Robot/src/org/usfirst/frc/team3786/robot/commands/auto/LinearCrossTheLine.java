package org.usfirst.frc.team3786.robot.commands.auto;

import org.usfirst.frc.team3786.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LinearCrossTheLine extends Command {
	private boolean finished = false;
	private long shortTimeMillis = 3000;
	private long startingTimeMillis;
	private int startingPosition;
	private double x;
	private double y;
    public LinearCrossTheLine(int startingPosition) {
    	this.startingPosition = startingPosition;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	long now = System.currentTimeMillis();
    	x = 0.0;
    	y = 0.0;
    	startingTimeMillis = System.currentTimeMillis();
    	if(startingPosition == 1) {
    		x = -0.1;
    		y = 1.0;
    		startingTimeMillis = now;
    	}else if(startingPosition == 2) {
    		x = 0.0;
    		y = 0.0;
    		System.err.println("Don't use this for starting postion 2");
    		startingTimeMillis = now;
    	}else if(startingPosition == 3) {
    		x = 0.1;
    		y = 1.0;
    		startingTimeMillis = now;
    	}else {
    		System.err.println("Wrong starting Position");
    		startingTimeMillis = now;
    		return;
    	}
    	finished = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.err.println("RUNNING!!!!" + startingPosition);
    	long now = System.currentTimeMillis();
    	if(now - startingTimeMillis > shortTimeMillis) {
    		finished = true; 
    		return;
    	}
    	Robot.instance.getDriveSubsystem().gyroAssistedDrive(-x, y, 0.0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.err.println("Stopping");
    	Robot.instance.getDriveSubsystem().gyroAssistedDrive(0.0, 0.0, 0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
