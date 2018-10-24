package org.usfirst.frc.team3786.robot.util;

import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class GuitarController extends SuperXboxController {

	public GuitarController(int id, double deadzone) {
		this.id = id;
		this.deadzone = deadzone;
		this.controller = new edu.wpi.first.wpilibj.XboxController(id);
		buttonA = new JoystickButton(controller, 4); //1
		buttonB = new JoystickButton(controller, 3);  //2
		buttonX = new JoystickButton(controller, 2);  //3
		buttonY = new JoystickButton(controller, 1); //4
		bumperLeft = new JoystickButton(controller, 5);
		bumperRight = new JoystickButton(controller, 6);
		buttonBack = new JoystickButton(controller, 7); 
		buttonStart = new JoystickButton(controller, 8); 
		buttonLeftCenter = new JoystickButton(controller, 9); 
		buttonRightCenter = new JoystickButton(controller, 10); 
	}

	private double speed = 0.4;
	@Override
	public double leftStickY() {
		// TODO Auto-generated method stub
		
		int pov = pov();
		if (pov == 0) {
			return speed;
		}
		else if (pov == -1) {
			return 0.0;
		}
		else if (pov == 180) {
			return -speed;
		}
		return 0.0;
	}

	@Override
	public double leftStickX() {
		// TODO Auto-generated method stub
		int pov = pov();
		if (pov == 270) {
			return -speed;
		}
		else if (buttonY()) {
			return -speed;
		}
		else if (buttonA()) {
			return speed;
		}
		else if (pov == -1) {
			return 0.0;
		}
		else if (pov == 90) {
			return speed;
		}
		return 0.0;
	}

	@Override
	public double rightStickY() {
		// TODO Auto-generated method stub
		return super.rightStickY();
	}

	@Override
	public double rightStickX() {
		// TODO Auto-generated method stub
		if (buttonX()) {
			return speed;
		}
		else if (buttonB()) {
			return -speed;
		}
		else {
			return 0.0;
		}
	}

	@Override
	public double leftTrigger() {
		// TODO Auto-generated method stub
		return super.leftTrigger();
	}

	@Override
	public double rightTrigger() {
		// TODO Auto-generated method stub
		return super.rightTrigger();
	}

	@Override
	public boolean buttonA() {
		// TODO Auto-generated method stub
		return super.buttonA();
	}

	@Override
	public boolean buttonB() {
		// TODO Auto-generated method stub
		return super.buttonB();
	}

	@Override
	public boolean buttonX() {
		// TODO Auto-generated method stub
		return super.buttonX();
	}

	@Override
	public boolean buttonY() {
		// TODO Auto-generated method stub
		return super.buttonY();
	}

	@Override
	public boolean buttonBack() {
		// TODO Auto-generated method stub
		return super.buttonBack();
	}
	@Override
	public boolean buttonStart() {
		// TODO Auto-generated method stub
		return super.buttonStart();
	}

	@Override
	public boolean buttonLeftCenter() {
		// TODO Auto-generated method stub
		return super.buttonLeftCenter();
	}

	@Override
	public boolean buttonRightCenter() {
		// TODO Auto-generated method stub
		return super.buttonRightCenter();
	}

	@Override
	public boolean bumperLeft() {
		// TODO Auto-generated method stub
		return super.bumperLeft();
	}

	@Override
	public boolean bumperRight() {
		// TODO Auto-generated method stub
		return super.bumperRight();
	}

	@Override
	public int pov() {
		// TODO Auto-generated method stub
		return super.pov();
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return super.getID();
	}

}
