/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3786.robot;

import org.usfirst.frc.team3786.robot.commands.TankDriveCommand;
import org.usfirst.frc.team3786.robot.subsystems.MecanumSubsystem;
import org.usfirst.frc.team3786.robot.subsystems.TwoWheelSubsystem;
import org.usfirst.frc.team3786.robot.util.ColorSensorUtil;
import org.usfirst.frc.team3786.robot.util.GyroUtil;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
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

	private UsbCamera camera;

	private TwoWheelSubsystem twoWheelSubsystem;

	private MecanumSubsystem mecanumSubsystem;
	
	private DrivetrainType drivetrainType = DrivetrainType.TWO_WHEEL;

	private int driverStationNumber;
	private String gameSpecificMessage;

	private Command autonomousCommand;
	private SendableChooser<Command> chooser = new SendableChooser<>();

	public ColorSensorUtil colorSenseUtil = new ColorSensorUtil();
	public GyroUtil gyroUtil = GyroUtil.getInstance();

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		instance = this;
		RobotMap.controllerMappings();
		this.setPeriod(DEFAULT_PERIOD);

		driverStationNumber = DriverStation.getInstance().getLocation();
		gameSpecificMessage = DriverStation.getInstance().getGameSpecificMessage();

		camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(320, 240);
		camera.setFPS(30);

		//chooser.addDefault("Default Auto", new AutonomousCrossTheLineCommand(driverStationNumber));
		SmartDashboard.putData("Auto mode", chooser);
	}

	/**
	 * 
	 * This function is called once each time the robot enters Disabled mode. You
	 * can use it to reset any subsystem information you want to clear when the
	 * robot is disabled.
	 */
	@Override
	public void disabledInit() {
		/**
		MecanumDriveCommand.instance.setDisableX(false);
		MecanumDriveCommand.instance.setDisableY(false);
		MecanumDriveCommand.instance.setSpeedLimit(true);
		**/
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
		TankDriveCommand.getInstance().start();
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		gyroUtil.run();
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
	
	public TwoWheelSubsystem getTwoWheelSubsystem() {
		if (this.drivetrainType == DrivetrainType.TWO_WHEEL) {
			if (this.twoWheelSubsystem != null) {
				return this.twoWheelSubsystem;
			} else {
				this.twoWheelSubsystem = new TwoWheelSubsystem();
				return this.twoWheelSubsystem;
			}
		}
		return null;
	}
	
	public MecanumSubsystem getMecanumSubsystem() {
		if (this.drivetrainType == DrivetrainType.MECANUM) {
			if (this.mecanumSubsystem != null) {
				return this.mecanumSubsystem;
			} else {
				this.mecanumSubsystem = new MecanumSubsystem();
				return this.mecanumSubsystem;
			}
		}
		return null;
	}
	
	public enum DrivetrainType {
		MECANUM(),
		TWO_WHEEL();
	}
}