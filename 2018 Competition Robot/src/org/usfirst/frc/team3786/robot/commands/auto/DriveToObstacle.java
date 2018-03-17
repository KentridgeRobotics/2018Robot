package org.usfirst.frc.team3786.robot.commands.auto;

import org.usfirst.frc.team3786.robot.Robot;
import org.usfirst.frc.team3786.robot.util.UltraSonicDistance;

import edu.wpi.first.wpilibj.command.Command;

public class DriveToObstacle extends Command{

		
		@Override
		protected void initialize() {
		}

		private boolean finished = false;
		private double ySpeed;
		private double xSpeed;
		private double rotation;
		
		public DriveToObstacle(int millisToEnd, double xSpeed, double ySpeed, double rotation){
			this.ySpeed = ySpeed;
			this.xSpeed = xSpeed;
			this.rotation = rotation; 
		}
		
		  protected void execute() {
		    	System.err.println("RUNNING!!!!");
		    	Robot.instance.getDriveSubsystem().gyroAssistedDrive(xSpeed, ySpeed, rotation);
		    	if(UltraSonicDistance.getInstance().getDistance() <= 36.0) {
		    		finished = true; 
		    	}
		    }
		  
		  protected void end() {
		    	System.err.println("Stopping");
		    	Robot.instance.getDriveSubsystem().gyroAssistedDrive(0.0, 0.0, 0.0);
		    	finished = false; 
		    }
		  
		@Override
		protected boolean isFinished() {
			// TODO Auto-generated method stub
			return finished;
		}
	}



