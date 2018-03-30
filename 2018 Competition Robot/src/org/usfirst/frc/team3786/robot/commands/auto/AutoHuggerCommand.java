package org.usfirst.frc.team3786.robot.commands.auto;

import org.usfirst.frc.team3786.robot.subsystems.HuggerJointSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoHuggerCommand extends Command {
	private long now;
	private long currentTimeMillis; 
	private boolean finished;
    public AutoHuggerCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(HuggerJointSubsystem.getInstance());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	now = System.currentTimeMillis();
    	HuggerJointSubsystem.getInstance().setSpeed(-0.5);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentTimeMillis = System.currentTimeMillis();
    	if(currentTimeMillis - now < 300) {
    		finished = false;
    	}else {
    		finished = true; 
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("Stopping");{
    		HuggerJointSubsystem.getInstance().setSpeed(0);
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
