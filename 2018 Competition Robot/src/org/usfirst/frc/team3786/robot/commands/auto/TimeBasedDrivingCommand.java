package org.usfirst.frc.team3786.robot.commands.auto;

import org.usfirst.frc.team3786.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TimeBasedDrivingCommand extends Command{
	private int startingPosition;
	private long startingTimeMillis;
	private boolean finished = false;
	private double ySpeed;
	private double xSpeed;
	private long millisToStart;
	private long millisToEnd;
	
	TimeBasedDrivingCommand(int startingPosition, int millisToStart, int millisToEnd, double xSpeed, double ySpeed){
		this.startingPosition = startingPosition;
		this.millisToStart = millisToStart;
		this.millisToEnd = millisToEnd;
		this.ySpeed = ySpeed;
		this.xSpeed = xSpeed;
	}
	
	  protected void execute() {
	    	System.err.println("RUNNING!!!!" + startingPosition);
	    	long now = System.currentTimeMillis();
	    	if (now - startingTimeMillis < millisToStart) {
	    		return;
	    	}
	    	else if (now - startingTimeMillis > millisToEnd) {
	    		finished = true; 
	    		return;
	    	}
	    	Robot.instance.getDriveSubsystem().gyroAssistedDrive(xSpeed, ySpeed, 0.0);
	    }
	  
	  protected void end() {
	    	System.err.println("Stopping");
	    	Robot.instance.getDriveSubsystem().gyroAssistedDrive(0.0, 0.0, 0.0);
	    }
	  
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return finished;
	}
}
