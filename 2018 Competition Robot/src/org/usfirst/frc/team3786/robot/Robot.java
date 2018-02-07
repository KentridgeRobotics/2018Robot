/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3786.robot;

import org.usfirst.frc.team3786.robot.commands.DisableXCommand;
import org.usfirst.frc.team3786.robot.commands.DisableYCommand;
import org.usfirst.frc.team3786.robot.commands.ExampleCommand;
import org.usfirst.frc.team3786.robot.commands.SpeedLimitCommand;
import org.usfirst.frc.team3786.robot.commands.MandibleCloseCommand;
import org.usfirst.frc.team3786.robot.commands.MandibleOpenCommand;
import org.usfirst.frc.team3786.robot.commands.MandibleStopCommand;
import org.usfirst.frc.team3786.robot.commands.MecanumDriveCommand;
import org.usfirst.frc.team3786.robot.subsystems.TwoWheelSubsystem;
import org.usfirst.frc.team3786.robot.subsystems.WheelsSubsystem;
import org.usfirst.frc.team3786.robot.util.BNO055.CalData;
import org.usfirst.frc.team3786.robot.util.ColorSensorUtil;
import org.usfirst.frc.team3786.robot.util.GyroUtil;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */

public class Robot extends TimedRobot {

	public static Robot instance;

	private static int cam_fps = 30;
	
	private static UsbCamera camera;

	public static final TwoWheelSubsystem twoWheelSubsystem = new TwoWheelSubsystem();

	public static final WheelsSubsystem wheelsSubsystem = new WheelsSubsystem();

	private int driverStationNumber;
	private String gameSpecificMessage;

	public static OI oi;
	// public MecanumDrive mecanumDrive;
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	PowerDistributionPanel pdp;

	public ColorSensorUtil colorSenseUtil = new ColorSensorUtil();
	public GyroUtil gyroUtil = GyroUtil.getInstance();

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		instance = this;

		this.setPeriod(DEFAULT_PERIOD);

		// mecanumDrive = new MecanumDrive(null, null, null, null);
		
		camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(320, 240);
		camera.setFPS(cam_fps);
		
		chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", chooser);
		pdp = new PowerDistributionPanel();
		
		MandibleStopCommand mandibleStopCommand = new MandibleStopCommand();
		oi = new OI();
		OI.buttonA.whenPressed(new MandibleOpenCommand());
		OI.buttonA.whenReleased(mandibleStopCommand);
		OI.buttonB.whenPressed(new MandibleCloseCommand());
		OI.buttonB.whenReleased(mandibleStopCommand);
		OI.buttonX.whenPressed(new SpeedLimitCommand());
		OI.buttonBack.whenPressed(new DisableXCommand());
		OI.buttonStart.whenPressed(new DisableYCommand());
		
		driverStationNumber = DriverStation.getInstance().getLocation();
		gameSpecificMessage = DriverStation.getInstance().getGameSpecificMessage();
	}

	/**
	 * 
	 * This function is called once each time the robot enters Disabled mode. You
	 * can use it to reset any subsystem information you want to clear when the
	 * robot is disabled.
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
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
	 * remove all of the chooser code and uncomment the getString code to get the
	 * auto name from the text box below the Gyro
	 *
	 * <p>
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons to
	 * the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		 * switch(autoSelected) { case "My Auto": autonomousCommand = new
		 * MyAutoCommand(); break; case "Default Auto": default: autonomousCommand = new
		 * ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null) {
			autonomousCommand.start();
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
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
		MecanumDriveCommand.getInstance().start();

		CalData cal = gyroUtil.getCalibration();
		System.out.println("CALIBRATION: Sys=" + cal.sys + " Gyro=" + cal.gyro + " Accel=" + cal.accel + " Mag=" + cal.mag);
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		gyroUtil.run();

		// debug data
		SmartDashboard.putNumber("Battery Voltage", pdp.getVoltage());
		SmartDashboard.putNumber("VelX", gyroUtil.getVelX());
		SmartDashboard.putNumber("VelY", gyroUtil.getVelY());
		SmartDashboard.putNumber("DispX", gyroUtil.getDispX());
		SmartDashboard.putNumber("VelY", gyroUtil.getDispY());
		SmartDashboard.putNumberArray("VectorGyro", gyroUtil.getVector());
		SmartDashboard.putNumberArray("VectorAccel", gyroUtil.getAccel());
		SmartDashboard.putData(Robot.wheelsSubsystem);
		SmartDashboard.putNumber("PID Error", wheelsSubsystem.getPIDController().getError());

	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}

	public int getDriverStationNumber() {
		return driverStationNumber;
	}

	public String getGameSpecificMessage() {
		return gameSpecificMessage;
	}
}
