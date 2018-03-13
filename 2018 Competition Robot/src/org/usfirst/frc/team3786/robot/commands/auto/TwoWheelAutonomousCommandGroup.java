package org.usfirst.frc.team3786.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TwoWheelAutonomousCommandGroup extends CommandGroup{
	TwoWheelAutonomousCommandGroup(){
	/* Go forward one second
	 * Turn 90 degrees to the left
	 * Go forward two seconds
	 * Turn 90 degrees to the right
	 * Go forward two seconds
	 * 
	 * 
	 */
	addSequential(new TimeBasedDrivingCommand(0,1,0.0,1.0,0.0));
	addSequential(new TimeBasedDrivingCommand(0,1,0.0,0.0,1.0));
	addSequential(new TimeBasedDrivingCommand(0,1,1.0,0.0,0.0));
	addSequential(new TimeBasedDrivingCommand(0,1,0.0,0.0,-1.0));
	addSequential(new TimeBasedDrivingCommand(0,2,0.0,1.0,0.0));	
	}
}
