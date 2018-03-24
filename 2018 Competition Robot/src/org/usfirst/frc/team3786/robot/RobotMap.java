/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3786.robot;

import org.usfirst.frc.team3786.robot.commands.DEBUGDOWNCOMMAND;
import org.usfirst.frc.team3786.robot.commands.DEBUGUPCOMMAND;
import org.usfirst.frc.team3786.robot.commands.DisableXCommand;
import org.usfirst.frc.team3786.robot.commands.DisableYCommand;
import org.usfirst.frc.team3786.robot.commands.HuggerInCommand;
import org.usfirst.frc.team3786.robot.commands.HuggerJointDownCommand;
import org.usfirst.frc.team3786.robot.commands.HuggerJointUpCommand;
import org.usfirst.frc.team3786.robot.commands.HuggerOutCommand;
import org.usfirst.frc.team3786.robot.commands.HuggerStopCommand;
import org.usfirst.frc.team3786.robot.commands.TowerLowerCommand;
import org.usfirst.frc.team3786.robot.commands.TowerRaiseCommand;
import org.usfirst.frc.team3786.robot.commands.TowerStopCommand;
import org.usfirst.frc.team3786.robot.util.ButtonMappingType;
import org.usfirst.frc.team3786.robot.util.XboxButton;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;

	// Mecanum Motors
	public static int backRightMotor = 1;
	public static int backLeftMotor = 2;
	public static int frontRightMotor = 3;
	public static int frontLeftMotor = 4;

	// Two Wheel Motors
	public static int leftMotor = 1;
	public static int rightMotor = 2;
	
	// Hugger Motor
	public static int huggerMotor = 5;
	
	// Tower Motor
	public static int towerMotor = 6;

	// Controllers
	public static int mainXboxPort = 0;
	public static double xboxStickDeadzone = 0.13;
	public static int secondaryXboxPort = 1;
	
	public static void controllerMappings() {
		// Initially remove all mappings
		
		// Set new mappings
		HuggerStopCommand huggerStopCommand = new HuggerStopCommand();
		OI.getSecondaryController().bumperLeft.whenPressed(new HuggerOutCommand());
		OI.getSecondaryController().bumperLeft.whenReleased(huggerStopCommand);
		OI.getSecondaryController().bumperRight.whenPressed(new HuggerInCommand());
		OI.getSecondaryController().bumperRight.whenReleased(huggerStopCommand);
		
		TowerStopCommand towerStopCommand = new TowerStopCommand();
		OI.getSecondaryController().buttonA.whenPressed(new TowerLowerCommand());
		OI.getSecondaryController().buttonA.whenReleased(towerStopCommand);
		OI.getSecondaryController().buttonB.whenPressed(new TowerRaiseCommand());
		OI.getSecondaryController().buttonB.whenReleased(towerStopCommand);
		
		OI.getMainController().buttonA.whenPressed(new HuggerJointUpCommand());
		OI.getMainController().buttonB.whenPressed(new HuggerJointDownCommand());
		}
	
}
