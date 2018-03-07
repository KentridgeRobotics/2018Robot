package org.usfirst.frc.team3786.robot.commands.auto;

import org.usfirst.frc.team3786.robot.Robot;
import org.usfirst.frc.team3786.robot.subsystems.TowerSubsystem;
import org.usfirst.frc.team3786.robot.util.DistanceSensor;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class NavigateToSwitchCommand extends Command {
	private double x;
	private double y;
	private DistanceSensor sensor;
    public NavigateToSwitchCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	sensor = new DistanceSensor();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		TowerSubsystem.getInstance().setMotorSpeed(0.0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double angle = sensor.getRadians();
    	double distance = sensor.getDistance();
    	Robot.instance.getDriveSubsystem().gyroAssistedDrive(distance, angle, 0.0);
    	
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
