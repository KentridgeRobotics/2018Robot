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
	private Direction direction;
	
	private enum Direction {
		LEFT, RIGHT
	}
	
	protected void initiallize() {
		startDegree = GyroUtil.getInstance().getHeadingContinuous();
		this.isRunning = true;
	}
	
	
	RotationBasedDriving(double rotation, double rotationSpeed){
		this.rotation = rotation;
		while(rotation > 180) {
			this.rotation -= 360;
		}
		while(rotation < -180) {
			this.rotation += 360;
		}
		
		if(this.rotation >= 0) {
			direction = Direction.RIGHT;
		}else {
			direction = Direction.LEFT;
		}
	
		this.rotationSpeed = rotationSpeed;
	}
	
	
	protected void execute() {
		currentDegree = GyroUtil.getInstance().getHeadingContinuous();
		System.err.println("RUNNING!!!!");
		if(Math.abs(currentDegree - startDegree) < Math.abs(rotation)) {
			if(direction == Direction.RIGHT) {
				Robot.instance.getDriveSubsystem().gyroAssistedDrive(0.0, 0.0, rotationSpeed);
			} else {
				Robot.instance.getDriveSubsystem().gyroAssistedDrive(0.0, 0.0, -rotationSpeed);
			}
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
		// TODO Auto-generated method stub
		return !isRunning;
	}

}
