package org.usfirst.frc.team3786.robot.commands.auto;

import org.usfirst.frc.team3786.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Robot Drives forward until it passes black line. Basic Code to working with
 * 
 * 3 different actions
 * start by going forward, 
 * then go left or right for enough to go past the switch
 * go forward again until it is over the line
 * and then stop.
 */

public class AutonomousCrossTheLineCommand extends Command {
	private int StartingPosition;
	
	enum commandStep {
		Go_Forward,
		Go_Sideways,
		Goto_Line,
		Finish
		
	}
	
	private commandStep currentStep;
	private long startingMilliSeconds;
	private long shortTimeMilliSeconds = 500;
	private long longTimeMilliSeconds = 2000;
	
	public AutonomousCrossTheLineCommand(int StartingPosition) {
		this.StartingPosition = StartingPosition;
		requires((Subsystem) Robot.instance.getDriveSubsystem());
		currentStep = commandStep.Go_Forward;
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		startingMilliSeconds = System.currentTimeMillis();
	}
	
	void InitialForward() {
		long now = System.currentTimeMillis();
		if(now - startingMilliSeconds > shortTimeMilliSeconds) {
			currentStep = commandStep.Go_Sideways;
			startingMilliSeconds = now;
			return;
		}
		
		Robot.instance.getDriveSubsystem().gyroAssistedDrive(0.0, 1.0, 0.0);
	}
	
	void GoSideWays() {
		double x = 0.0;
		double y = 0.0;
		
		long now = System.currentTimeMillis();
		if(StartingPosition == 1) {
			x = -0.5;
			startingMilliSeconds = now;
		}
		else if(StartingPosition == 2) {
			x = 1.0;
			startingMilliSeconds = now;
		}
		else if(StartingPosition == 3) {
			x = 0.5;
			startingMilliSeconds = now;
		}
		else {
			System.err.println("Wrong Starting Position");
			startingMilliSeconds = now;
			return;
		}
		
		Robot.instance.getDriveSubsystem().gyroAssistedDrive(x, y, 0.0);
	}
	
	void FinalForward() {
		long now = System.currentTimeMillis();
		if(now - startingMilliSeconds > longTimeMilliSeconds) {
			currentStep = commandStep.Finish;
			return;
		}
		
		Robot.instance.getDriveSubsystem().gyroAssistedDrive(0.0, 1.0, 0.0);
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(currentStep == commandStep.Go_Forward) {
			InitialForward();
			return;
			
		}
		else if(currentStep == commandStep.Go_Sideways) {
			GoSideWays();
			return;
		}
		else if(currentStep == commandStep.Goto_Line) {
			FinalForward();
			return;
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (Robot.instance.colorSenseUtil.onBlackTape()) {
			return true;
		}
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.instance.getDriveSubsystem().gyroAssistedDrive(0.0, 0.0, 0.0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {

	}
}
