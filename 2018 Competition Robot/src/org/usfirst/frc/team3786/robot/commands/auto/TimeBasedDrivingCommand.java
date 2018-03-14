package org.usfirst.frc.team3786.robot.commands.auto;

import org.usfirst.frc.team3786.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TimeBasedDrivingCommand extends Command{
	private long startingTimeMillis;
	@Override
	protected void initialize() {
		startingTimeMillis = System.currentTimeMillis();
	}

	private boolean finished = false;
	private double ySpeed;
	private double xSpeed;
	private long millisToStart;
	private long millisToEnd;
	private double rotation;
	
	TimeBasedDrivingCommand( int millisToStart, int millisToEnd, double xSpeed, double ySpeed, double rotation){
		this.millisToStart = millisToStart;
		this.millisToEnd = millisToEnd;
		this.ySpeed = ySpeed;
		this.xSpeed = xSpeed;
		this.rotation = rotation; 
	}
	
	  protected void execute() {
	    	System.err.println("RUNNING!!!!");
	    	long now = System.currentTimeMillis();
	    	if (now - startingTimeMillis < millisToStart) {
	    		return;
	    	}
	    	else if (now - startingTimeMillis > millisToEnd) {
	    		finished = true; 
	    		return;
	    	}
	    	Robot.instance.getDriveSubsystem().gyroAssistedDrive(xSpeed, ySpeed, rotation);
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
