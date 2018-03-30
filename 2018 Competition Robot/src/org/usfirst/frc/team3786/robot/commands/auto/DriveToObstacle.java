package org.usfirst.frc.team3786.robot.commands.auto;

import org.usfirst.frc.team3786.robot.Robot;
import org.usfirst.frc.team3786.robot.util.GyroUtil;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveToObstacle extends Command{

private long startingTimeMillis;
private long millisToRun;
private double desiredHeading; 
private boolean finished = false;
private double speed;
private DriveForwardParameters driveForwardParameters;
	
	@Override
	protected void initialize() {
		desiredHeading = GyroUtil.getInstance().getHeadingContinuous(); 
    	finished = false;
    	startingTimeMillis = System.currentTimeMillis();
    	if(driveForwardParameters != null) {
			millisToRun = driveForwardParameters.millisToRun;
		}
    	
	}
	 
	public DriveToObstacle(double speed, long maxMillis, DriveForwardParameters  driveForwardParameters){
		this.speed = -speed; 
		millisToRun = maxMillis; 
		this.driveForwardParameters = driveForwardParameters; 
		
	}
	
	  protected void execute() {
		    long now = System.currentTimeMillis();
		    double rotation = 0.0; 
	    	
	    	double currentHeading = GyroUtil.getInstance().getHeadingContinuous();
	    	rotation = 0.25 * (desiredHeading - currentHeading)/(Math.abs(desiredHeading)+Math.abs(currentHeading)+1); 
	    	Robot.instance.getDriveSubsystem().gyroAssistedDrive(0.0, speed, rotation);
	    	SmartDashboard.putNumber("rotation", rotation);
	    	if (now - startingTimeMillis > millisToRun) {
	    		finished = true; 
	    		return;
	    	}
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



