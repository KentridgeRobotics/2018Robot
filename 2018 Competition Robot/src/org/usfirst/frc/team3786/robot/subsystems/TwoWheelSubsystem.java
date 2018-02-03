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
	private WPI_TalonSRX leftfront;
	private WPI_TalonSRX rightfront;
	private WPI_TalonSRX rightback;
	private WPI_TalonSRX leftback;
	public TwoWheelSubsystem() {
		leftfront = new WPI_TalonSRX(1);
		rightfront = new WPI_TalonSRX(2);
		leftback = new WPI_TalonSRX(3);
		rightback = new WPI_TalonSRX(4);
		
	}
	@Override
	public void setMotorSpeeds(double leftFrontSpeed, double leftBackSpeed, double rightBackSpeed, double rightFrontSpeed) {
		leftfront.set(leftFrontSpeed);
		rightfront.set(rightFrontSpeed);
		leftback.set(-leftBackSpeed);
		rightback.set(-rightBackSpeed);
		// TODO Auto-generated method stub
		System.out.println("Set speed: " + leftFrontSpeed + " " + leftBackSpeed + "" + rightFrontSpeed + "" + rightBackSpeed);
	}
	

	@Override
	public void setBrakeMode(boolean isBraking) {
		NeutralMode mode;
		if(isBraking) {
			mode = NeutralMode.Brake;
		} else {
			mode = NeutralMode.Coast;
		}
		leftfront.setNeutralMode(mode);
		rightfront.setNeutralMode(mode);
		leftback.setNeutralMode(mode);
		rightback.setNeutralMode(mode);
				
	}

	@Override
//	public void setTwoMotorSpeeds(double leftSpeed, double rightSpeed) {
	//	leftfront.set(leftSpeed);
		//leftback.set(-leftSpeed);
		//right.set(rightSpeed);
		
		
	//}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		leftfront.configOpenloopRamp(0.2, 0);
		leftback.configOpenloopRamp(0.2, 0);
		rightfront.configOpenloopRamp(0.2, 0);
		rightback.configOpenloopRamp(0.2, 0);
	}
	@Override
	public void setTwoMotorSpeeds(double leftSpeed, double rightSpeed) {
		// TODO Auto-generated method stub
		
	}

}
