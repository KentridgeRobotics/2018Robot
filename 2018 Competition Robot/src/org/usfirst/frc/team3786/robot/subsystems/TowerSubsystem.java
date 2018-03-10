package org.usfirst.frc.team3786.robot.subsystems;

import org.usfirst.frc.team3786.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.Faults;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class TowerSubsystem extends Subsystem {

	private static TowerSubsystem instance;

	public static TowerSubsystem getInstance() {
		if (instance == null)
			instance = new TowerSubsystem();
		return instance;
	}

	private WPI_TalonSRX motor;

	public TowerSubsystem() {
		motor = new WPI_TalonSRX(RobotMap.towerMotor);
	}

	public void setMotorSpeed(double speed) {
		motor.set(speed);
	}

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
	public String getControllerFaults() {
		Faults faults = new Faults();
		 motor.getFaults(faults);
		 return faults.toString();
	}
}
