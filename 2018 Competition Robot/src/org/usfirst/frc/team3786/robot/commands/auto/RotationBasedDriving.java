package org.usfirst.frc.team3786.robot.commands.auto;

import org.usfirst.frc.team3786.robot.Robot;
import org.usfirst.frc.team3786.robot.util.GyroUtil;

import edu.wpi.first.wpilibj.command.Command;

public class RotationBasedDriving extends Command{
	private double currentDegree; 
	private double rotation;
	private double rotationSpeed;
	private double startDegree;
	private boolean isRunning;

	
	protected void initiallize() {
		startDegree = GyroUtil.getInstance().getHeadingContinuous();
		this.isRunning = true;
	}
	
	
	RotationBasedDriving(double rotation, double rotationSpeed){
		this.rotation = rotation;
		this.rotationSpeed = rotationSpeed;
	}
	
	
	protected void execute() {
	    
	    currentDegree = GyroUtil.getInstance().getHeadingContinuous();
		System.err.println("RUNNING!!!!");
		if(Math.abs(currentDegree - startDegree + rotation) < 0.1) {
			if(currentDegree - startDegree + rotation < 0) {
			    Robot.instance.getDriveSubsystem().gyroAssistedDrive(0.0, 0.0, -rotationSpeed);	
			}
				Robot.instance.getDriveSubsystem().gyroAssistedDrive(0.0, 0.0, rotationSpeed);
		} else {
			isRunning = false;
		}
	}
	
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
