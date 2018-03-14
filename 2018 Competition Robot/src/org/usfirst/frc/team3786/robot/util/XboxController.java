package org.usfirst.frc.team3786.robot.util;

import java.util.ArrayList;
import java.util.HashMap;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

public class XboxController {
	
	private final int id;
	
	private final double deadzone;
	
	private final edu.wpi.first.wpilibj.XboxController controller;

	private final edu.wpi.first.wpilibj.buttons.Button buttonA; // A button
	private final edu.wpi.first.wpilibj.buttons.Button buttonB; // B button
	private final edu.wpi.first.wpilibj.buttons.Button buttonX; // X button
	private final edu.wpi.first.wpilibj.buttons.Button buttonY; // Y button
	private final edu.wpi.first.wpilibj.buttons.Button bumperLeft; // Left bumper
	private final edu.wpi.first.wpilibj.buttons.Button bumperRight; // Right bumper
	private final edu.wpi.first.wpilibj.buttons.Button buttonBack; // Back button
	private final edu.wpi.first.wpilibj.buttons.Button buttonStart; // Start button
	private final edu.wpi.first.wpilibj.buttons.Button buttonLeftCenter; // Left Center button
	private final edu.wpi.first.wpilibj.buttons.Button buttonRightCenter; // Right Center button
	
	private HashMap<XboxButton, ArrayList<Mapping>> mappings = new HashMap<>();
	
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
	
	public boolean getButtonA() {
		return this.buttonA.get();
	}
	
	public boolean getButtonB() {
		return this.buttonB.get();
	}
	
	public boolean getButtonX() {
		return this.buttonX.get();
	}
	
	public boolean getButtonY() {
		return this.buttonY.get();
	}
	
	public boolean getBumperLeft() {
		return this.bumperLeft.get();
	}
	
	public boolean getBumperRight() {
		return this.bumperRight.get();
	}
	
	public boolean getButtonBack() {
		return this.buttonBack.get();
	}
	
	public boolean getButtonStart() {
		return this.buttonStart.get();
	}
	
	public boolean getButtonLeftCenter() {
		return this.buttonLeftCenter.get();
	}
	
	public boolean getButtonRightCenter() {
		return this.buttonRightCenter.get();
	}
	
	public void setMapping(XboxButton button, ButtonMappingType ButtonMappingType, Command command) {
		if (mappings.containsKey(button)) {
			ArrayList<Mapping> mapList = mappings.get(button);
			for (Mapping map : mapList) {
				if (map.getButtonMappingType() == ButtonMappingType) {
					Mapping newMap = new Mapping(button, ButtonMappingType, command);
					mapList.remove(map);
					mapList.add(newMap);
					setMappingValue(getButton(button), ButtonMappingType, command);
					mappings.put(button, mapList);
					return;
				}
			}
		} else {
			ArrayList<Mapping> mapList = new ArrayList<>();
			Mapping map = new Mapping(button, ButtonMappingType, command);
			mapList.add(map);
			setMappingValue(getButton(button), ButtonMappingType, command);
			mappings.put(button, mapList);
			return;
		}
	}
	
	public void setMapping(Mapping mapping) {
		XboxButton button = mapping.getButton();
		ButtonMappingType ButtonMappingType = mapping.getButtonMappingType();
		Command command = mapping.getCommand();
		if (mappings.containsKey(button)) {
			ArrayList<Mapping> mapList = mappings.get(button);
			for (Mapping map : mapList) {
				if (map.getButtonMappingType() == ButtonMappingType) {
					mapList.remove(map);
					mapList.add(mapping);
					setMappingValue(getButton(button), ButtonMappingType, command);
					mappings.put(button, mapList);
					return;
				}
			}
		} else {
			ArrayList<Mapping> mapList = new ArrayList<>();
			mapList.add(mapping);
			setMappingValue(getButton(button), ButtonMappingType, command);
			mappings.put(button, mapList);
			return;
		}
	}
	
	private edu.wpi.first.wpilibj.buttons.Button getButton(XboxButton button) {
		switch(button) {
		case A:
			return buttonA;
		case B:
			return buttonB;
		case X:
			return buttonX;
		case Y:
			return buttonY;
		case BUMPER_LEFT:
			return bumperLeft;
		case BUMPER_RIGHT:
			return bumperRight;
		case BACK:
			return buttonBack;
		case START:
			return buttonStart;
		case LEFT_CENTER:
			return buttonLeftCenter;
		case RIGHT_CENTER:
			return buttonRightCenter;
		default:
			return null;
		}
	}
	
	private static void setMappingValue(edu.wpi.first.wpilibj.buttons.Button button, ButtonMappingType ButtonMappingType, Command command) {
		switch(ButtonMappingType) {
		case WHEN_PRESSED:
			button.whenPressed(command);
			break;
		case WHEN_RELEASED:
			button.whenReleased(command);
			break;
		case WHEN_ACTIVE:
			button.whenActive(command);
			break;
		case WHEN_INACTIVE:
			button.whenInactive(command);
			break;
		case WHILE_HELD:
			button.whileHeld(command);
			break;
		case WHILE_ACTIVE:
			button.whileActive(command);
			break;
		case CANCEL_WHEN_ACTIVE:
			button.cancelWhenActive(command);
			break;
		case CANCEL_WHEN_PRESSED:
			button.cancelWhenPressed(command);
			break;
		case TOGGLE_WHEN_ACTIVE:
			button.toggleWhenActive(command);
			break;
		case TOGGLE_WHEN_PRESSED:
			button.toggleWhenPressed(command);
			break;
		}
	}
	
	public void removeMappings() {
		for (ArrayList<Mapping> mappings : mappings.values()) {
			for (Mapping map : mappings) {
				setMappingValue(getButton(map.getButton()), map.getButtonMappingType(), null);
			}
		}
		mappings = new HashMap<>();
	}
}

class Mapping {
	
	private XboxButton button;
	private ButtonMappingType ButtonMappingType;
	private Command command;
	
	Mapping(XboxButton button, ButtonMappingType ButtonMappingType, Command command) {
		this.button = button;
		this.ButtonMappingType = ButtonMappingType;
		this.command = command;
	}
	
	public XboxButton getButton() {
		return this.button;
	}
	
	public ButtonMappingType getButtonMappingType() {
		return this.ButtonMappingType;
	}
	
	public Command getCommand() {
		return this.command;
	}
	
}