/*
 * 
 */
package org.usfirst.frc.team3786.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class TwoWheelSubsystem extends Subsystem implements Drive {
	private static TwoWheelSubsystem instance;
	
	public static TwoWheelSubsystem getInstance() {
		if(instance == null)
			instance = new TwoWheelSubsystem();
		return instance;		
	}
	private WPI_TalonSRX left;
	private WPI_TalonSRX right;
	public TwoWheelSubsystem() {
		left = new WPI_TalonSRX(2);
		right = new WPI_TalonSRX(1);
	}
	@Override
	public void setMotorSpeeds(double leftFrontSpeed, double leftBackSpeed, double rightBackSpeed, double rightFrontSpeed) {
		left.set(leftFrontSpeed);
		right.set(rightFrontSpeed);
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void setBrakeMode(boolean isBraking) {
		NeutralMode mode;
		if(isBraking) {
			mode = NeutralMode.Brake;
		} else {
			mode = NeutralMode.Coast;
		}
		left.setNeutralMode(mode);
		right.setNeutralMode(mode);
		
		
		
	}

	@Override
	public void setTwoMotorSpeeds(double leftSpeed, double rightSpeed) {
		left.set(-leftSpeed);
		right.set(rightSpeed);
		System.out.println("Set speed: " + leftSpeed + " " + rightSpeed);
		
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		left.configOpenloopRamp(1.0, 0);
		right.configOpenloopRamp(1.0, 0);
	}

}
