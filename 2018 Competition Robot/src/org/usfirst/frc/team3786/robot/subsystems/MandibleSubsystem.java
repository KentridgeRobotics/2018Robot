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
	private WPI_TalonSRX left;
	private WPI_TalonSRX right;
	public MandibleSubsystem() {
		left = new WPI_TalonSRX(5);
		right = new WPI_TalonSRX(6);
		left.configOpenloopRamp(0.2, 0);
		right.configOpenloopRamp(0.2, 0);
	}
	

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

	public void setTwoMotorSpeeds(double Speed) {
		left.set(-Speed);
		right.set(Speed);
		System.out.println("Set speed: " + Speed);
		
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		
	}

}

