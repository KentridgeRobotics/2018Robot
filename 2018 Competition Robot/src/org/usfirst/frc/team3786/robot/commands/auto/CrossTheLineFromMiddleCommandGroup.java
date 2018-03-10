package org.usfirst.frc.team3786.robot.commands.auto;

import org.usfirst.frc.team3786.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CrossTheLineFromMiddleCommandGroup extends Command{
	private int startingPosition;
	private long startingTimeMillis;
	private boolean finished = false;
	private double y;   
	CrossTheLineFromMiddleCommandGroup(int startingPosition){
		this.startingPosition = startingPosition;
	}
	  protected void execute() {
	    	System.err.println("RUNNING!!!!" + startingPosition);
	    	long now = System.currentTimeMillis();
	    	if (now - startingTimeMillis < 5000L) {
	    		return;
	    	}
	    	else if (now - startingTimeMillis > 8000L) {
	    		finished = true; 
	    		return;
	    	}
	    	Robot.instance.getDriveSubsystem().gyroAssistedDrive(0.0, y, 0.0);
	    }
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return finished;
	}
}
