/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3786.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	private static XboxController xboxCon = new XboxController(RobotMap.xboxPort);

	public static Button buttonA = new JoystickButton(xboxCon, 1); // A button
	public static Button buttonB = new JoystickButton(xboxCon, 2); // B button
	public static Button buttonX = new JoystickButton(xboxCon, 3); // X button
	public static Button buttonY = new JoystickButton(xboxCon, 4); // Y button
	public static Button bumperL = new JoystickButton(xboxCon, 5); // Left bumper
	public static Button bumperR = new JoystickButton(xboxCon, 6); // Right bumper
	public static Button buttonBack = new JoystickButton(xboxCon, 7); // Back button
	public static Button buttonStart = new JoystickButton(xboxCon, 8); // Start button
	public static Button buttonLCenter = new JoystickButton(xboxCon, 9); // Left Center button
	public static Button buttonRCenter = new JoystickButton(xboxCon, 10); // Right Center button

	public static double getLeftStickY() {
		double n = xboxCon.getY(Hand.kLeft);
		if (Math.abs(n) <= RobotMap.xboxStickDeadzone)
			return 0.0;
		else
			return n;
	}

	public static double getLeftStickX() {
		double n = xboxCon.getX(Hand.kLeft);
		if (Math.abs(n) <= RobotMap.xboxStickDeadzone)
			return 0.0;
		else
			return n;

	}

	public static double getRightStickY() {
		double n = xboxCon.getY(Hand.kRight);
		if (Math.abs(n) <= RobotMap.xboxStickDeadzone)
			return 0.0;
		else
			return n;
	}

	public static double getRightStickX() {
		double n = xboxCon.getX(Hand.kRight);
		if (Math.abs(n) <= RobotMap.xboxStickDeadzone)
			return 0.0;
		else
			return n;
	}

	public static double getLeftTrigger() {
		double n = xboxCon.getTriggerAxis(Hand.kLeft);
		return n;
	}

	public static double getRightTrigger() {
		double n = xboxCon.getTriggerAxis(Hand.kRight);
		return n;
	}

	public static boolean aButton() {

		return xboxCon.getAButton();
	}

	public static boolean bButton() {
		return xboxCon.getBButton();
	}

	public static boolean xButton() {
		return xboxCon.getXButton();
	}

	public static boolean yButton() {
		return xboxCon.getYButton();
	}

	public static boolean backButton() {
		return xboxCon.getBackButton();
	}

	public static boolean startButton() {
		return xboxCon.getStartButton();
	}

	public static boolean leftCenter() {
		return xboxCon.getStickButton(Hand.kLeft);
	}

	public static boolean rightCenter() {
		return xboxCon.getStickButton(Hand.kRight);
	}

	public static boolean leftBumper() {
		return xboxCon.getBumper(Hand.kLeft);
	}

	public static boolean rightBumper() {
		return xboxCon.getBumper(Hand.kRight);
	}

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}
