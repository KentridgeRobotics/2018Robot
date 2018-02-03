package org.usfirst.frc.team3786.robot.commands;

import org.usfirst.frc.team3786.robot.OI;
import org.usfirst.frc.team3786.robot.Robot;
import org.usfirst.frc.team3786.robot.subsystems.WheelsSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class MecanumDriveCommand extends Command {
	
		public static MecanumDriveCommand inst;
		
		public static MecanumDriveCommand getInstance() {
			if(inst == null)
				inst = new MecanumDriveCommand();
			return inst;
		}
		
		public MecanumDriveCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		requires(WheelsSubsystem.getInstance());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	WheelsSubsystem.getInstance();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// When the number is negative, the wheels go forwards.
    	// When the number is positive, the wheels go backwards.
    	double x = OI.getLeftStickX();
    	double y = OI.getLeftStickY();
    	x = x * x * x;
    	y = y * y * y;
    	System.out.println("Left Joystick X = " + x);
    	System.out.println("Left Joystick Y = " + y);
    	Robot.wheelsSubsystem.setXboxDrive(0.0, y);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
