package org.usfirst.frc.team3786.robot.figure8;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class AutoDriveSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void updateDistances(double elapsedTime) {
    	
    }
    public void setDriveSpeed(double leftInchesPerSecond, double rightInchesPerSecond) {
    	// Do something.
    }
    
    public double getLeftEncoderDistanceInches() {
    	
    }
    public double getRightEncoderDistanceInches() {
    	
    }
}

