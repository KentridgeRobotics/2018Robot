package org.usfirst.frc.team3786.robot.subsystems;

/*
 * 
 */

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class MandibleSubsystem extends Subsystem {
	private static MandibleSubsystem instance;
	
	public static MandibleSubsystem getInstance() {
		if(instance == null)
			instance = new MandibleSubsystem();
		return instance;		
	}
	private WPI_TalonSRX motor;
	public MandibleSubsystem() {
		motor = new WPI_TalonSRX(5);
		motor.configOpenloopRamp(0.2, 0);
	}
	

	public void setBrakeMode(boolean isBraking) {
		NeutralMode mode;
		if(isBraking) {
			mode = NeutralMode.Brake;
		} else {
			mode = NeutralMode.Coast;
		}
		motor.setNeutralMode(mode);
		
		
		
	}

	public void setTwoMotorSpeeds(double Speed) {
		motor.set(Speed);
		System.out.println("Set speed: " + Speed);
		
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		
	}

}

