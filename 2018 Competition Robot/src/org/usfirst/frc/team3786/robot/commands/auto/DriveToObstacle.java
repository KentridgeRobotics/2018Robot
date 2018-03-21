package org.usfirst.frc.team3786.robot.commands.auto;

import org.usfirst.frc.team3786.robot.Robot;
import org.usfirst.frc.team3786.robot.util.GyroUtil;
import org.usfirst.frc.team3786.robot.util.UltraSonicDistance;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveToObstacle extends Command{

		
		@Override
		protected void initialize() {
			desiredHeading = GyroUtil.getInstance().getHeadingContinuous(); 
	    	finished = false;
	    	tooCloseCount = 0; 
		}
		
		private double desiredHeading; 
		private boolean finished = false;
		private double speed;
		private int tooCloseCount;
		public DriveToObstacle(double speed){
			this.speed = speed; 
		}
		
		  protected void execute() {
			    double rotation = 0.0; 
		    	System.err.println("RUNNING!!!!" + speed);
		    	double currentHeading = GyroUtil.getInstance().getHeadingContinuous();
		    	rotation = 0.25 * (desiredHeading - currentHeading)/(Math.abs(desiredHeading)+Math.abs(currentHeading)+1); 
		    	Robot.instance.getDriveSubsystem().gyroAssistedDrive(0.0, speed, rotation);
		    	SmartDashboard.putNumber("rotation", rotation);
		    	if(UltraSonicDistance.getInstance().getDistance() <= 33.0) {
		    		tooCloseCount++; 
		    	}
		    	if(tooCloseCount > 10) {
		    		finished = true; 
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



