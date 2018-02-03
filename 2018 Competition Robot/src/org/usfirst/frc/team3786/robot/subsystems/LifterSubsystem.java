package org.usfirst.frc.team3786.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LifterSubsystem extends Subsystem {
	
private static LifterSubsystem instance;
	
	public static LifterSubsystem getInstance() {
		if(instance == null)
			instance = new LifterSubsystem();
		return instance;
	}
	
	private WPI_TalonSRX Lifter;
	private WPI_TalonSRX Winch;
	
	
	public LifterSubsystem() {
		Lifter = new WPI_TalonSRX(7);
		Winch = new WPI_TalonSRX(8);
	}

	public void setBrakeMode(boolean isBraking) {
		NeutralMode mode;
		if(isBraking) {
			mode = NeutralMode.Brake;
		} else {
			mode = NeutralMode.Coast;
		}
		Lifter.setNeutralMode(mode);

		
		
		
	}
	
	public void setLiftSpeed(double speed) {
		Lifter.set(speed);
	}
	
	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
	
	

}
