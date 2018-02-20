package org.usfirst.frc.team3786.robot.subsystems;

import org.usfirst.frc.team3786.robot.RobotMap;

/*
 * 
 */

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class HuggerSubsystem extends Subsystem {
	
	private static HuggerSubsystem instance;

	public static HuggerSubsystem getInstance() {
		if (instance == null)
			instance = new HuggerSubsystem();
		return instance;
	}

	private WPI_TalonSRX motor;

	public HuggerSubsystem() {
		motor = new WPI_TalonSRX(RobotMap.huggerMotor);
		motor.configOpenloopRamp(0.2, 0);
		motor.setNeutralMode(NeutralMode.Brake);
	}

	public void setMotorSpeed(double Speed) {
		motor.set(Speed);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

}
