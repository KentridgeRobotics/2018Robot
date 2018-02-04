package org.usfirst.frc.team3786.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class TowerSubsystem extends Subsystem {
	
private static TowerSubsystem instance;
	
	public static TowerSubsystem getInstance() {
		if(instance == null)
			instance = new TowerSubsystem();
		return instance;
	}
	
	private WPI_TalonSRX Elevator;

	
	
	public TowerSubsystem() {
		Elevator = new WPI_TalonSRX(7);
	}

	public void setBrakeMode(boolean isBraking) {
		NeutralMode mode;
		if(isBraking) {
			mode = NeutralMode.Brake;
		} else {
			mode = NeutralMode.Coast;
		}
		Elevator.setNeutralMode(mode);

		
		
		
	}
	
	public void setLiftSpeed(double speed) {
		Elevator.set(speed);
	}
	
	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
	
	

}
