/*
 * Xbox Controller Class
 * @author Matthew Bayisa & Eric Rodriguez
 * we are not good Java programmers.
 */

package org.usfirst.frc.team3786.robot;

// Imports for Xbox Controller
import edu.wpi.first.wpilibj.Joystick;

public class XboxController extends Joystick {
	enum ControllerButton {
		A,
		B,
		X,
		Y,
		BACK,
		GUIDE,
		START,
		LEFTSTICK,
		RIGHTSTICK,
		LEFTBUMPER,
		RIGHTBUMPER, 
		DPAD_UP,
		DPAD_DOWN,
		DPAD_LEFT,
		DPAD_RIGHT,
		DPAD_UP_RIGHT,
		DPAD_DOWN_RIGHT,
		DPAD_DOWN_LEFT,
		DPAD_UP_LEFT;
	}

	public XboxController(final int port) {
		super(port);

	}
}
