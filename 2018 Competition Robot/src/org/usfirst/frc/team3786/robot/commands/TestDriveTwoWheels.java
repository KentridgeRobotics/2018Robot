package org.usfirst.frc.team3786.robot.commands;

import org.usfirst.frc.team3786.robot.OI;
import org.usfirst.frc.team3786.robot.Robot;
import org.usfirst.frc.team3786.robot.subsystems.TwoWheelSubsystem;
import org.usfirst.frc.team3786.robot.subsystems.WheelsSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TestDriveTwoWheels extends Command {

	public static TestDriveTwoWheels inst;

	private TwoWheelSubsystem subSystemInst;
	
	private boolean speedLimit = false;
	
	private boolean xDisable = false;
	private boolean yDisable = false;
	private boolean isRunning;
	
	private double max;
	
	public static TestDriveTwoWheels getInstance() {
		if (inst == null)
			inst = new TestDriveTwoWheels();
		return inst;
	}

	public TestDriveTwoWheels() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(TwoWheelSubsystem.getInstance());
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		isRunning = true;
		subSystemInst = Robot.twoWheelSubsystem.getInstance();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// Drive controls
		double x = OI.getLeftStickX();
		double y = OI.getLeftStickY();
		
		if(x > max) {
			max = x;
		}
		if(y > max) {
			max = y;
		}
		double leftSpeed = 0;
		double rightSpeed = 0;

		if (this.xDisable)
			x = 0;
		if (this.yDisable)
			y = 0;
		
		SmartDashboard.putBoolean("Working", isRunning);
		SmartDashboard.putNumber("X", x);
		SmartDashboard.putNumber("Y", y);
		
		if(x > 0) {
			leftSpeed = y;
			rightSpeed = max - (x);
		} else if (x < 0){
			leftSpeed = max + x;
			rightSpeed = y;
		} else {
			rightSpeed = y;
			leftSpeed = y;
		}
		if(speedLimit) {
			rightSpeed /= 3;
			leftSpeed /= 3;
		}
		
		// Update motors with controls
		//Robot.wheelsSubsystem.setXboxDrive(x, -y, turn);
		
		
		subSystemInst.setMotorSpeeds(leftSpeed, rightSpeed);
		
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if(!isRunning) {
			return true;
		}
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		
	}
	
	public boolean setSpeedLimit(boolean speedLimit) {
		this.speedLimit = speedLimit;
		return this.speedLimit;
	}
	
	public boolean getSpeedLimit() {
		return this.speedLimit;
	}
	
	public boolean getDisableX() {
		return this.xDisable;
	}
	
	public boolean getDisableY() {
		return this.yDisable;
	}
	
	public void toggleSpeedLimit() {
		if (this.speedLimit)
			this.speedLimit = false;
		else
			this.speedLimit = true;
	}
	
	public void toggleX() {
		if (this.xDisable)
			this.xDisable = false;
		else
			this.xDisable = true;
	}
	
	public void toggleY() {
		if (this.yDisable)
			this.yDisable = false;
		else
			this.yDisable = true;
	}
}
