package org.usfirst.frc.team3786.robot.commands.auto;

import org.usfirst.frc.team3786.robot.Robot;
import org.usfirst.frc.team3786.robot.util.GyroUtil;

import edu.wpi.first.wpilibj.command.Command;

public class RotationBasedDriving extends Command{
	private double currentDegree; 
	private final double rotation;
	private final double rotationSpeed;
	private double startDegree;
	private boolean isRunning;
	private double currentError; 
	private double absCurrentError; 
	private static double slowDownDegrees = 10.0; 
	@Override
	protected void initialize() {
		startDegree = GyroUtil.getInstance().getHeadingContinuous();
		this.isRunning = true;
	}
	
	
	public RotationBasedDriving(double rotation, double rotationSpeed){
		this.rotation = rotation;
		this.rotationSpeed = rotationSpeed;
	}
	
	@Override
	protected void execute() {
	    
	    currentDegree = GyroUtil.getInstance().getHeadingContinuous();
	    currentError = (currentDegree - (startDegree + rotation));
	    absCurrentError = Math.abs(currentError); 
	    double speed = rotationSpeed;
	    if (absCurrentError < slowDownDegrees){
	    	speed*= absCurrentError/slowDownDegrees;
	    }
		if(absCurrentError > 1.0) {
			if(currentError < 0) {
			    Robot.instance.getDriveSubsystem().gyroAssistedDrive(0.0, 0.0, -speed);	
			}
			else {
				Robot.instance.getDriveSubsystem().gyroAssistedDrive(0.0, 0.0, speed);
			}
		} else {
			isRunning = false;
		}
	}
	
	@Override
	protected void end() {
    	System.err.println("Stopping");
    	Robot.instance.getDriveSubsystem().gyroAssistedDrive(0.0, 0.0, 0.0);
    	isRunning = false;
    }
	
	@Override
	protected boolean isFinished() {
		return !isRunning;
	}

}
