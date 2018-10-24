package org.usfirst.frc.team3786.robot.util;


import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class XboxController extends SuperXboxController {
	
	public XboxController(int id) {
		this(id, 0.0);
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
	
	
}