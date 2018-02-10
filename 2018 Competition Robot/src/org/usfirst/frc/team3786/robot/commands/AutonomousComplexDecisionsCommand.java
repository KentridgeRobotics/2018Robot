package org.usfirst.frc.team3786.robot.commands;

import org.usfirst.frc.team3786.robot.subsystems.MecanumSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Robot runs decisions based on decisions made through decisions making class.
 * <ul>
 * <li>Implement Accelerometer</li>
 * <li>Implement Color Censor</li>
 * <li>Implement Gyroscope</li>
 * </ul>
 */

// 1. Given the Initial Position (1, 2, 3)
// 2. Determine the Direction to steer based on
// 3. Check direction of target using vision target
// 4. Possibly also implement distance sensor to determine the current state of
// the
// Robot
//
//
public class AutonomousComplexDecisionsCommand extends Command {
	private int startingPosition;
	private String colorPositions;
	private Direction initialDirection;
	private int targetNumber;

	public enum Direction {
		LEFT, RIGHT
	}

	/**
	 * 
	 * @param startingPosition
	 * @param gameSpecificMessage
	 * @param targetNumber:
	 *            Either 1 or 2, decides on the target goal to aim for
	 * 
	 */
	public AutonomousComplexDecisionsCommand(int startingPosition, String gameSpecificMessage, int targetNumber) {
		this.startingPosition = startingPosition;
		this.targetNumber = targetNumber;
		colorPositions = gameSpecificMessage;

		requires(MecanumSubsystem.getInstance());
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		if (targetNumber > 2 && targetNumber < 1) {
			targetNumber = 1;
		}

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		MecanumSubsystem.getInstance().setDirectionSpeed(0, 1.0, 0);
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
