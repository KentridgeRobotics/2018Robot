package org.usfirst.frc.team3786.robot.commands.auto;

import org.usfirst.frc.team3786.robot.subsystems.HuggerSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DropOffCubeCommand extends Command {
	private long now;
	private long currentTimeMillis; 
	private boolean finished;
    public DropOffCubeCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(HuggerSubsystem.getInstance());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	now = System.currentTimeMillis();
    	HuggerSubsystem.getInstance().setMotorSpeed(-1.0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentTimeMillis = System.currentTimeMillis();
    	if(currentTimeMillis - now < 3000) {
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
    		HuggerSubsystem.getInstance().setMotorSpeed(0);
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
