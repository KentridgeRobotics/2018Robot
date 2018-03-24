package org.usfirst.frc.team3786.robot.subsystems;



import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class HuggerJointSubsystem extends Subsystem{
	private static HuggerJointSubsystem instance;

	public static HuggerJointSubsystem getInstance() {
		if (instance == null)
			instance = new HuggerJointSubsystem();
		return instance;
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	Spark motor; 
	public HuggerJointSubsystem() {
		motor = new Spark(2);
	}
	public void setSpeed(double speed){
		motor.set(speed);
	}
}
