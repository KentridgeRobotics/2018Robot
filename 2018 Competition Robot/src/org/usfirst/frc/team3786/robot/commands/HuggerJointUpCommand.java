package org.usfirst.frc.team3786.robot.commands;

import org.usfirst.frc.team3786.robot.subsystems.HuggerJointSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class HuggerJointUpCommand extends Command{
	
	public HuggerJointUpCommand(){
		requires(HuggerJointSubsystem.getInstance());
	}
	protected void initialize(){
		HuggerJointSubsystem.getInstance().setSpeed(0.5);
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
