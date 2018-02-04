package org.usfirst.frc.team3786.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class LiftSubsystem extends Subsystem{
	
	private static LiftSubsystem instance;
	
	public static LiftSubsystem getInstance() {
		if(instance == null)
			instance = new LiftSubsystem();
		return instance;
	}
	
	
	private WPI_TalonSRX Winch;
	
	public LiftSubsystem() {
		Winch = new WPI_TalonSRX(8);
	}                                          
	
	public void setWinchSpeed(double speed) {
		Winch.set(speed);
	}
	
	public void setBrakeMode(boolean isBraking) {
		NeutralMode mode;
		if(isBraking) {
			mode = NeutralMode.Brake;
		} else {
			mode = NeutralMode.Coast;
		}
		Winch.setNeutralMode(mode);
	
		
		
		
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
}
