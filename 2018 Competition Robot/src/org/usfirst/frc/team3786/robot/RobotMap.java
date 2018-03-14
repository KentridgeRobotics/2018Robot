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
		OI.getMainController().removeMappings();
		OI.getSecondaryController().removeMappings();
		
		// Set new mappings
		HuggerStopCommand huggerStopCommand = new HuggerStopCommand();
		OI.getSecondaryController().setMapping(XboxButton.BUMPER_LEFT, ButtonMappingType.WHEN_PRESSED, new HuggerOutCommand());
		OI.getSecondaryController().setMapping(XboxButton.BUMPER_LEFT, ButtonMappingType.WHEN_RELEASED, huggerStopCommand);
		OI.getSecondaryController().setMapping(XboxButton.BUMPER_RIGHT, ButtonMappingType.WHEN_PRESSED, new HuggerInCommand());
		OI.getSecondaryController().setMapping(XboxButton.BUMPER_RIGHT, ButtonMappingType.WHEN_RELEASED, huggerStopCommand);
		OI.getMainController().setMapping(XboxButton.BACK, ButtonMappingType.WHEN_PRESSED, new DisableXCommand());
		OI.getMainController().setMapping(XboxButton.START, ButtonMappingType.WHEN_PRESSED, new DisableYCommand());

		TowerStopCommand towerStopCommand = new TowerStopCommand();
		OI.getSecondaryController().setMapping(XboxButton.A, ButtonMappingType.WHEN_PRESSED, new TowerLowerCommand());
		OI.getSecondaryController().setMapping(XboxButton.A, ButtonMappingType.WHEN_RELEASED, towerStopCommand);
		OI.getSecondaryController().setMapping(XboxButton.B, ButtonMappingType.WHEN_PRESSED, new TowerRaiseCommand());
		OI.getSecondaryController().setMapping(XboxButton.B, ButtonMappingType.WHEN_RELEASED, towerStopCommand);
	}
	
	public static void debug() {
		// Initially remove all mappings
		OI.getMainController().removeMappings();
		OI.getSecondaryController().removeMappings();
		
		// Setup debug controls
		OI.getMainController().setMapping(XboxButton.A, ButtonMappingType.WHEN_PRESSED, new DEBUGUPCOMMAND());
		OI.getMainController().setMapping(XboxButton.B, ButtonMappingType.WHEN_PRESSED, new DEBUGDOWNCOMMAND());
	}
}
