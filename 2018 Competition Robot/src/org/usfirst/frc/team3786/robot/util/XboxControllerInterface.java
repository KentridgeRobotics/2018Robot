package org.usfirst.frc.team3786.robot.util;

public interface XboxControllerInterface {

	double leftStickY();

	double leftStickX();

	double rightStickY();

	double rightStickX();

	double leftTrigger();

	double rightTrigger();

	boolean buttonA();

	boolean buttonB();

	boolean buttonX();

	boolean buttonY();

	boolean buttonBack();

	boolean buttonStart();

	boolean buttonLeftCenter();

	boolean buttonRightCenter();

	boolean bumperLeft();

	boolean bumperRight();

	int pov();

	int getID();

}