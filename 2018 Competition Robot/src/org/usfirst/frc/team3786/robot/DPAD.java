package org.usfirst.frc.team3786.robot;

public enum DPAD {
	DPAD_UP(0),
	DPAD_UP_RIGHT(45),
	DPAD_RIGHT(90),
	DPAD_DOWN_RIGHT(135),
	DPAD_DOWN(180),
	DPAD_DOWN_LEFT(225),
	DPAD_LEFT(270),
	DPAD_UP_LEFT(315);

	public final int angle;

	private DPAD(int angle) {
		this.angle = angle;
	}
}