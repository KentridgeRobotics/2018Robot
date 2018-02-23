package org.usfirst.frc.team3786.robot.util;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class XboxController {
	
	private final int id;
	
	private final double deadzone;
	
	private final edu.wpi.first.wpilibj.XboxController controller;

	private final Button buttonA; // A button
	private final Button buttonB; // B button
	private final Button buttonX; // X button
	private final Button buttonY; // Y button
	private final Button bumperLeft; // Left bumper
	private final Button bumperRight; // Right bumper
	private final Button buttonBack; // Back button
	private final Button buttonStart; // Start button
	private final Button buttonLeftCenter; // Left Center button
	private final Button buttonRightCenter; // Right Center button
	
	public XboxController(int id) {
		this.id = id;
		this.deadzone = 0.0;
		this.controller = new edu.wpi.first.wpilibj.XboxController(id);
		buttonA = new JoystickButton(controller, 1);
		buttonB = new JoystickButton(controller, 2);
		buttonX = new JoystickButton(controller, 3);
		buttonY = new JoystickButton(controller, 4);
		bumperLeft = new JoystickButton(controller, 5);
		bumperRight = new JoystickButton(controller, 6);
		buttonBack = new JoystickButton(controller, 7);
		buttonStart = new JoystickButton(controller, 8);
		buttonLeftCenter = new JoystickButton(controller, 9);
		buttonRightCenter = new JoystickButton(controller, 10);
	}
	
	public XboxController(int id, double deadzone) {
		this.id = id;
		this.deadzone = deadzone;
		this.controller = new edu.wpi.first.wpilibj.XboxController(id);
		buttonA = new JoystickButton(controller, 1);
		buttonB = new JoystickButton(controller, 2);
		buttonX = new JoystickButton(controller, 3);
		buttonY = new JoystickButton(controller, 4);
		bumperLeft = new JoystickButton(controller, 5);
		bumperRight = new JoystickButton(controller, 6);
		buttonBack = new JoystickButton(controller, 7);
		buttonStart = new JoystickButton(controller, 8);
		buttonLeftCenter = new JoystickButton(controller, 9);
		buttonRightCenter = new JoystickButton(controller, 10);
	}


	public double leftStickY() {
		double n = controller.getY(Hand.kLeft);
		if (Math.abs(n) <= deadzone)
			return 0.0;
		else
			return n;
	}

	public double leftStickX() {
		double n = controller.getX(Hand.kLeft);
		if (Math.abs(n) <= deadzone)
			return 0.0;
		else
			return n;

	}

	public double rightStickY() {
		double n = controller.getY(Hand.kRight);
		if (Math.abs(n) <= deadzone)
			return 0.0;
		else
			return n;
	}

	public double rightStickX() {
		double n = controller.getX(Hand.kRight);
		if (Math.abs(n) <= deadzone)
			return 0.0;
		else
			return n;
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
	
	public Button getButtonA() {
		return this.buttonA;
	}
	
	public Button getButtonB() {
		return this.buttonB;
	}
	
	public Button getButtonX() {
		return this.buttonX;
	}
	
	public Button getButtonY() {
		return this.buttonY;
	}
	
	public Button getBumperLeft() {
		return this.bumperLeft;
	}
	
	public Button getBumperRight() {
		return this.bumperRight;
	}
	
	public Button getButtonBack() {
		return this.buttonBack;
	}
	
	public Button getButtonStart() {
		return this.buttonStart;
	}
	
	public Button getButtonLeftCenter() {
		return this.buttonLeftCenter;
	}
	
	public Button getButtonRightCenter() {
		return this.buttonRightCenter;
	}

}
