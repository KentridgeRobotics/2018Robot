/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3786.robot;

import org.usfirst.frc.team3786.robot.commands.DisableXCommand;
import org.usfirst.frc.team3786.robot.commands.DisableYCommand;
import org.usfirst.frc.team3786.robot.commands.HuggerInCommand;
import org.usfirst.frc.team3786.robot.commands.HuggerOutCommand;
import org.usfirst.frc.team3786.robot.commands.HuggerStopCommand;

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
	public static int backLeftMotor = 2;
	public static int backRightMotor = 1;
	public static int frontLeftMotor = 4;
	public static int frontRightMotor = 5;

	// Two Wheel Motors
	public static int leftMotor = 1;
	public static int rightMotor = 2;
	
	// Hugger Motor
	public static int huggerMotor = 5;
	
	// Tower Motor
	public static int towerMotor = 6;
	
	// Lift Motor
	public static int liftMotor = 7;

	// Controllers
	public static int xboxPort = 0;
	public static double xboxStickDeadzone = 0.13;
	public static int joystickPort = 1;

	public static void controllerMappings() {
		HuggerStopCommand huggerStopCommand = new HuggerStopCommand();
		OI.bumperL.whenPressed(new HuggerOutCommand());
		OI.bumperL.whenReleased(huggerStopCommand);
		OI.bumperR.whenPressed(new HuggerInCommand());
		OI.bumperR.whenReleased(huggerStopCommand);
		OI.buttonBack.whenPressed(new DisableXCommand());
		OI.buttonStart.whenPressed(new DisableYCommand());
	}
	// System.out.println(NetworkTableInstance.getDefault().getTable("SmartDashboard").getEntry("cubeR").getDouble(0.0));
}
