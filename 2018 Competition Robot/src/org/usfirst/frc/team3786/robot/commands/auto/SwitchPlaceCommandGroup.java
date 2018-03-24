package org.usfirst.frc.team3786.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SwitchPlaceCommandGroup extends CommandGroup {
	static double speed = 0.5; 
    public SwitchPlaceCommandGroup(int startingPosition, boolean isLeft) {
        
    	if(startingPosition == 1) {
    		addSequential(new TimeBasedDrivingCommand((long)1000, 0.0, speed ,0.0));
//  	    addSequential(new RotationBasedDriving(90.0,speed)); 
    	    if(isLeft) {
//  	    	addSequential(new TimeBasedDrivingCommand((long)5000, 0.0, speed, 0.0));
    	    }else{
 // 	    	addSequential(new TimeBasedDrivingCommand((long) 100, 0.0, speed, 0.0)); 
    	    }
    	}
    	
    	
    	//addSequential(new NavigateToCubeCommand());
    	//addSequential(new GetCubeCommand());
    	//addSequential(new LiftCubeCommand());
    	//addSequential(new DropOffCubeCommand());
    	
    	// Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
