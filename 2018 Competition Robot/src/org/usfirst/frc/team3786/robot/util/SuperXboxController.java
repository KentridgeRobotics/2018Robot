package org.usfirst.frc.team3786.robot.util;

import edu.wpi.first.wpilibj.GenericHID.Hand;

public class SuperXboxController {

	protected int id;
	protected double deadzone;
	public edu.wpi.first.wpilibj.XboxController controller;
	public edu.wpi.first.wpilibj.buttons.Button buttonA;
	public edu.wpi.first.wpilibj.buttons.Button buttonB;
	public edu.wpi.first.wpilibj.buttons.Button buttonX;
	public edu.wpi.first.wpilibj.buttons.Button buttonY;
	public edu.wpi.first.wpilibj.buttons.Button bumperLeft;
	public edu.wpi.first.wpilibj.buttons.Button bumperRight;
	public edu.wpi.first.wpilibj.buttons.Button buttonBack;
	public edu.wpi.first.wpilibj.buttons.Button buttonStart;
	public edu.wpi.first.wpilibj.buttons.Button buttonLeftCenter;
	public edu.wpi.first.wpilibj.buttons.Button buttonRightCenter;

	public SuperXboxController() {
		super();
	}

	public double leftStickY() {
		double n = controller.getY(Hand.kLeft);
		if (Math.abs(n) <= deadzone)
			return 0.0;
		else {
			double c = (Math.abs(n) - deadzone) / (1.0 - deadzone);
			return (n > 0) ?  c: -c;
		}
	}

	public double leftStickX() {
		double n = controller.getX(Hand.kLeft);
		if (Math.abs(n) <= deadzone)
			return 0.0;
		else {
			double c = (Math.abs(n) - deadzone) / (1.0 - deadzone);
			return (n > 0) ?  c: -c;
		}
	
	}

	public double rightStickY() {
		double n = controller.getY(Hand.kRight);
		if (Math.abs(n) <= deadzone)
			return 0.0;
		else {
			double c = (Math.abs(n) - deadzone) / (1.0 - deadzone);
			return (n > 0) ?  c: -c;
		}
	}

	public double rightStickX() {
		double n = controller.getX(Hand.kRight);
		if (Math.abs(n) <= deadzone)
			return 0.0;
		else {
			double c = (Math.abs(n) - deadzone) / (1.0 - deadzone);
			return (n > 0) ?  c: -c;
		}
	}

	public double leftTrigger() {
		double n = controller.getTriggerAxis(Hand.kLeft);
		return n;
	}

	public double rightTrigger() {
		double n = controller.getTriggerAxis(Hand.kRight);
		return n;
	}

	public boolean buttonA() {
		return controller.getAButton();
	}

	public boolean buttonB() {
		return controller.getBButton();
	}

	public boolean buttonX() {
		return controller.getXButton();
	}

	public boolean buttonY() {
		return controller.getYButton();
	}

	public boolean buttonBack() {
		return controller.getBackButton();
	}

	public boolean buttonStart() {
		return controller.getStartButton();
	}

	public boolean buttonLeftCenter() {
		return controller.getStickButton(Hand.kLeft);
	}

	public boolean buttonRightCenter() {
		return controller.getStickButton(Hand.kRight);
	}

	public boolean bumperLeft() {
		return controller.getBumper(Hand.kLeft);
	}

	public boolean bumperRight() {
		return controller.getBumper(Hand.kRight);
	}

	public int pov() {
		return controller.getPOV();
	}

	public int getID() {
		return this.id;
	}

}