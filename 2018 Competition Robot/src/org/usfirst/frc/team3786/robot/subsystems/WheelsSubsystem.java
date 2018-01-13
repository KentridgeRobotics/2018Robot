package org.usfirst.frc.team3786.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class WheelsSubsystem extends Subsystem {

    
	
	private WPI_TalonSRX leftFront;
	private WPI_TalonSRX leftBack;
	private WPI_TalonSRX rightBack;
	private WPI_TalonSRX rightFront;
	
	public WheelsSubsystem() {
		leftFront = new WPI_TalonSRX(0);
		leftBack = new WPI_TalonSRX(0);
		rightBack = new WPI_TalonSRX(0);
		rightFront = new WPI_TalonSRX(0);
		
	}

	public void setMotorSpeeds(double leftFrontSpeed, double leftBackSpeed, double rightBackSpeed, double rightFrontSpeed) {
		leftFront.set(leftFrontSpeed);
		leftBack.set(leftBackSpeed);
		rightBack.set(rightBackSpeed);
		rightFront.set(rightFrontSpeed);
		
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

