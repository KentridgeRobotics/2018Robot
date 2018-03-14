package org.usfirst.frc.team3786.robot.commands.auto;

import org.usfirst.frc.team3786.robot.Robot;
import org.usfirst.frc.team3786.robot.util.GyroUtil;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

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
// 4. Possibly implement distance sensor to determine the current state of
// the robot.
// 
//
//
public class AutonomousComplexDecisionsCommand extends Command {
	private int startingPosition;
	private Direction initialDirection;
	private int targetNumber;
	private double distanceX;
	private double distanceY;
	private double desiredDistanceX;
	private double desiredDistanceY;
	private boolean isFinished;
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
	public AutonomousComplexDecisionsCommand(int startingPosition, int targetNumber, double desiredDistanceX, double desiredDistanceY) {
		this.startingPosition = startingPosition;
		this.targetNumber = targetNumber;
		this.desiredDistanceX = desiredDistanceX;
		this.desiredDistanceY = desiredDistanceY;

		requires((Subsystem) Robot.instance.getDriveSubsystem());
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
		Robot.instance.getDriveSubsystem().gyroAssistedDrive(0, 1.0, 0.0);
		distanceX = GyroUtil.getInstance().getDispX();
		distanceY = GyroUtil.getInstance().getDispY();  
		if(desiredDistanceX > 0.0) {
			if(desiredDistanceX > distanceX) {
				isFinished = false;
			}
		}else if(desiredDistanceX < 0.0) {
			if(desiredDistanceX < distanceX) {
				isFinished = false; 
			}
		}else isFinished = true; 
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {

		return isFinished;
	}

	// Called once after isFinished returns true
	protected void end() {

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {

	}
}
