/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3786.robot;

import org.usfirst.frc.team3786.robot.commands.ExampleCommand;
import org.usfirst.frc.team3786.robot.commands.MandibleCloseCommand;
import org.usfirst.frc.team3786.robot.commands.MandibleOpenCommand;
import org.usfirst.frc.team3786.robot.commands.MandibleStopCommand;
import org.usfirst.frc.team3786.robot.commands.TankDriveCommand;
import org.usfirst.frc.team3786.robot.subsystems.Drive;
import org.usfirst.frc.team3786.robot.subsystems.TwoWheelSubsystem;
import org.usfirst.frc.team3786.robot.util.GyroUtil;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import org.usfirst.frc.team3786.robot.subsystems.WheelsSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */




public class Robot extends TimedRobot {
	
	private static int cam_fps = 30;
	
	public static final TwoWheelSubsystem kTwoWheelSubsystem
			= new TwoWheelSubsystem();
	public static OI m_oi;
	public static Drive myDrive;
	//public MecanumDrive m_mecanumDrive;
	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();
	PowerDistributionPanel pdp;
	
	GyroUtil gUtil = new GyroUtil();

	

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_oi = new OI();
		myDrive = new TwoWheelSubsystem();
		//m_mecanumDrive = new MecanumDrive(null, null, null, null);
		XboxController xboxcontroller = new XboxController(5);
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(320, 240);
		camera.setFPS(cam_fps);
		m_chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", m_chooser);
		pdp = new PowerDistributionPanel();
		m_oi.buttonA.whenPressed(new MandibleOpenCommand());
		MandibleStopCommand mandibleStopCommand = new MandibleStopCommand();
		m_oi.buttonA.whenReleased(mandibleStopCommand);
		m_oi.buttonB.whenPressed(new MandibleCloseCommand());
		m_oi.buttonB.whenReleased(mandibleStopCommand);
	}

	/**
	 * 
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		m_autonomousCommand = m_chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
		TankDriveCommand.getInstance().start();
		
		
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Battery Voltage", pdp.getVoltage());
		SmartDashboard.putBoolean("A Button", OI.a_button());
		//m_mecanumDrive.drivePolar(Math.hypot(m_oi.getLeftStickX(), m_oi.getLeftStickY()), Math.atan2(m_oi.getLeftStickY(), m_oi.getLeftStickX()), 0);
		
		gUtil.run();
		System.out.println("TEST");
		
		SmartDashboard.putNumber("AccelX", gUtil.getAccel()[0]);
		SmartDashboard.putNumber("AccelY", gUtil.getAccel()[1]);
		
		SmartDashboard.putNumber("VelX", gUtil.getVelX());
		SmartDashboard.putNumber("VelY", gUtil.getVelY());
		SmartDashboard.putNumber("DispX", gUtil.getDispX());
		SmartDashboard.putNumber("VelY", gUtil.getDispY());
		
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
