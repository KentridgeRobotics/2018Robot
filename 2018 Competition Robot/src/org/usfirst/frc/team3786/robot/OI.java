/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3786.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
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
	
	private static XboxController myXbox = new XboxController(5);
	
	public static Button button1 = new JoystickButton(myXbox, 1); // A button
	public static Button button2 = new JoystickButton(myXbox, 2); // B button
	public static Button button3 = new JoystickButton(myXbox, 3); // X button
	public static Button button4 = new JoystickButton(myXbox, 4); // Y button
	
	public static double getLeftStickY() {
		return myXbox.getY(Hand.kLeft);
	}
	
	public static double getLeftStickX() {
		return myXbox.getX(Hand.kLeft);
		
	}
	
	public static double getRightStickY() {
		return myXbox.getY(Hand.kRight);
	}
	
	public static double getRightStickX() {
		return myXbox.getX(Hand.kRight);
	}
	
	public static boolean a_button() {
		
		return myXbox.getAButton();
	}
	
	public static boolean b_button() {
		return myXbox.getBButton();
	}
	
	public static boolean x_button() {
		return myXbox.getXButton();
	}
	
	public static boolean y_button() {
		return myXbox.getYButton();
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
