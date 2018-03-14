/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3786.robot;

import org.usfirst.frc.team3786.robot.commands.DEBUGCOMMAND;
import org.usfirst.frc.team3786.robot.commands.MecanumDriveCommand;
import org.usfirst.frc.team3786.robot.commands.auto.LinearCrossTheLine;
import org.usfirst.frc.team3786.robot.subsystems.ChargersDriveSubsystem;
import org.usfirst.frc.team3786.robot.subsystems.MecanumSubsystem;
import org.usfirst.frc.team3786.robot.subsystems.TowerSubsystem;
import org.usfirst.frc.team3786.robot.subsystems.TwoWheelSubsystem;
import org.usfirst.frc.team3786.robot.util.ColorSensorUtil;
import org.usfirst.frc.team3786.robot.util.GyroUtil;
import org.usfirst.frc.team3786.robot.util.LED;
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

	private SwitchSide[] switchSides;

	public static Robot instance;

	private UsbCamera camera;

	private ChargersDriveSubsystem driveSubsystem;

	public DrivetrainType drivetrainType = DrivetrainType.TWO_WHEEL;

	private int driverStationNumber;
	private String gameSpecificMessage;

	private Command autonomousCommand;
	private SendableChooser<Command> autonomousCommandChooser = new SendableChooser<Command>();
	public SendableChooser<Integer> autonomousThrottleChooser = new SendableChooser<Integer>();
	public ColorSensorUtil colorSenseUtil = new ColorSensorUtil();
	public GyroUtil gyroUtil = GyroUtil.getInstance();
	public org.usfirst.frc.team3786.robot.util.DistanceSensor distanceSensor = new org.usfirst.frc.team3786.robot.util.DistanceSensor();

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		instance = this;
		this.setPeriod(DEFAULT_PERIOD);

		driverStationNumber = DriverStation.getInstance().getLocation();
		LinearCrossTheLine linearCrossTheLineCommand = new LinearCrossTheLine(driverStationNumber);
		autonomousCommandChooser.addDefault("Cross the line linear", linearCrossTheLineCommand);
		autonomousCommandChooser.addObject("none", null);
		autonomousThrottleChooser.addObject("25%", 25);
		autonomousThrottleChooser.addObject("50%", 50);
		autonomousThrottleChooser.addObject("75%", 75);
		autonomousThrottleChooser.addDefault("100%", 100);
		camera = CameraServer.getInstance().startAutomaticCapture();
		if (camera != null) {
			camera.setResolution(320, 240);
			camera.setFPS(30);
		}
		// chooser.addDefault("Default Auto", new
		// AutonomousCrossTheLineCommand(driverStationNumber));
		SmartDashboard.putData("Auto mode", autonomousCommandChooser);
		SmartDashboard.putData("Auto throttle", autonomousThrottleChooser);
		MecanumDriveCommand.getInstance();
		switch (drivetrainType) {
		case MECANUM:
			System.out.println("#############################");
			System.out.println("# DRIVETRAIN SET TO MECANUM #");
			System.out.println("#############################");
			break;
		case TWO_WHEEL:
			System.out.println("##############################");
			System.out.println("# DRIVETRAIN SET TO TWOWHEEL #");
			System.out.println("##############################");
			break;
		}
	}

	/**
	 * 
	 * This function is called once each time the robot enters Disabled mode. You
	 * can use it to reset any subsystem information you want to clear when the
	 * robot is disabled.
	 */
	@Override
	public void disabledInit() {
		RobotMap.controllerMappings();
		MecanumDriveCommand.getInstance().setDisableX(false);
		MecanumDriveCommand.getInstance().setDisableY(false);
		LED.setRGB(0, 0, 0);
		gameSpecificMessage = null;
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		gameSpecificMessage = DriverStation.getInstance().getGameSpecificMessage();
		if (gameSpecificMessage != null) {
			char[] splitMessage = gameSpecificMessage.toCharArray();
			if (splitMessage.length == 3) {
				for (int i = 0; i < 3; i++) {
					if (splitMessage[i] == 'L') {
						switchSides[i] = SwitchSide.LEFT;
					} else if (splitMessage[i] == 'R') {
						switchSides[i] = SwitchSide.RIGHT;
					} else {
						switchSides[i] = null;
						break;
					}
				}
			}
		}
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
		String gameSpecificMessage = DriverStation.getInstance().getGameSpecificMessage();
		if (!gameSpecificMessage.equals(this.gameSpecificMessage)) {
			this.gameSpecificMessage = gameSpecificMessage;
		}
		if (gameSpecificMessage != null) {
			char[] splitMessage = gameSpecificMessage.toCharArray();
			if (splitMessage.length == 3) {
				for (int i = 0; i < 3; i++) {
					if (splitMessage[i] == 'L') {
						switchSides[i] = SwitchSide.LEFT;
					} else if (splitMessage[i] == 'R') {
						switchSides[i] = SwitchSide.RIGHT;
					} else {
						switchSides[i] = null;
						break;
					}
				}
			}
		}
		RobotMap.controllerMappings();
		autonomousCommand = autonomousCommandChooser.getSelected();

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
		RobotMap.controllerMappings();
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
		MecanumDriveCommand.getInstance().start();
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumberArray("Gyroscope", GyroUtil.getInstance().getVector());
		SmartDashboard.putNumber("Heading", GyroUtil.getInstance().getHeading());
		GyroUtil.getInstance().run();
		SmartDashboard.putNumber("Distance x", GyroUtil.getInstance().getDispX());
		SmartDashboard.putNumber("Distance y", GyroUtil.getInstance().getDispY());
		SmartDashboard.putString("TowerControllerFaults: ", TowerSubsystem.getInstance().getControllerFaults());
		LED.colorCycle();
	}

	@Override
	public void testInit() {
		DEBUGCOMMAND.getInstance().start();
		RobotMap.debug();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		LED.colorCycle();
	}

	public int getDriverStationNumber() {
		return driverStationNumber;
	}

	public String getGameSpecificMessage() {
		return gameSpecificMessage;
	}

	public ChargersDriveSubsystem getDriveSubsystem() {
		if (this.driveSubsystem != null)
			return this.driveSubsystem;
		else {
			if (drivetrainType == DrivetrainType.MECANUM) {
				this.driveSubsystem = new MecanumSubsystem();
				return this.driveSubsystem;
			} else if (drivetrainType == DrivetrainType.TWO_WHEEL) {
				this.driveSubsystem = new TwoWheelSubsystem();
				return this.driveSubsystem;
			}
		}
		return null;
	}

	public SwitchSide[] getSwitchSides() {
		return this.switchSides;
	}

	public enum DrivetrainType {
		MECANUM(), TWO_WHEEL();
	}

	public enum SwitchSide {
		LEFT(), RIGHT();
	}
}