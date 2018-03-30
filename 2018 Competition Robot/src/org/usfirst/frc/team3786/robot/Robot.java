/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3786.robot;

import org.usfirst.frc.team3786.robot.commands.MecanumDriveCommand;
import org.usfirst.frc.team3786.robot.commands.auto.DriveToObstacle;
import org.usfirst.frc.team3786.robot.commands.auto.FieldCallBacks;
import org.usfirst.frc.team3786.robot.commands.auto.RotationBasedDriving;
import org.usfirst.frc.team3786.robot.commands.auto.SwitchPlaceCommandGroup;
import org.usfirst.frc.team3786.robot.commands.auto.TimeBasedDrivingCommand;
import org.usfirst.frc.team3786.robot.subsystems.ChargersDriveSubsystem;
import org.usfirst.frc.team3786.robot.subsystems.MecanumSubsystem;
import org.usfirst.frc.team3786.robot.subsystems.TowerSubsystem;
import org.usfirst.frc.team3786.robot.subsystems.TwoWheelSubsystem;
import org.usfirst.frc.team3786.robot.util.ColorSensorUtil;
import org.usfirst.frc.team3786.robot.util.GyroUtil;
import org.usfirst.frc.team3786.robot.util.LED;
import org.usfirst.frc.team3786.robot.util.UltraSonicDistance;

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

	private SwitchSide[] switchSides = new SwitchSide[3];

	public static Robot instance;

	private UsbCamera camera;

	private ChargersDriveSubsystem driveSubsystem;

	public DrivetrainType drivetrainType = DrivetrainType.MECANUM;

	private int driverStationNumber;
	private String gameSpecificMessage;
	private FieldCallBacks fieldCallBacks = new FieldCallBacks();
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
	//	LinearCrossTheLine linearCrossTheLineCommand = new LinearCrossTheLine(driverStationNumber);
		autonomousCommandChooser.addDefault("Drive to obstacle", new DriveToObstacle(0.25, 15000, null));
		autonomousCommandChooser.addObject("Switch place group", new SwitchPlaceCommandGroup(this.fieldCallBacks));
		autonomousCommandChooser.addObject("Rotate", new RotationBasedDriving(90.0, 0.5,null));
		autonomousCommandChooser.addObject("Time based",  new TimeBasedDrivingCommand(1000, 0.0, 0.5, 0.0));
//		autonomousCommandChooser.addObject("Cross the line linear", linearCrossTheLineCommand);
//		autonomousCommandChooser.addObject("AutonomousMecanum", new MecanumAutonomousCommandGroup());
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
			if (gameSpecificMessage.length() == 3) {
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
	}
	
	private long millisForInches(int inches) {
		if (inches == 84)
		{
			return 1200;
		}
		if (inches == 22) {
			return 500;
		}
		if (inches == 140) {
			return 2800;
		}
		return 0;
	}

	public void switchPlaceConfig(int fieldPosition, boolean isMySideLeft) {
		/*
		 * If the position is 1 and switch color is blue, then drive forwards 84 inches, turn 90 degrees right, forwards 18 inches, 90 degrees left, forwards 84 inches
		 * OR
		 * If the position is 1 and switch color is red, then drive forwards 84 inches, turn 90 degrees left, forwards 32 inches, 90 degrees right, forwards 132 inches, 90 degrees right, forwards 36 inches
		 * 
		 * If the position is 2 and switch color is blue, then drive forwards 84 inches, turn 90 degrees left, forwards inches, 90 degrees right, forwards 84 inches
		 * OR
		 * If the position is 2 and switch color is red, then drive forwards 84 inches, turn 90 degrees right, forwards 54 inches, 90 degrees left, forwards 84 inches
		 */
		if (isMySideLeft) {
			if (fieldPosition == 1) {
				// OK, not tested
				this.fieldCallBacks.forward1.millisToRun = millisForInches(84);
				this.fieldCallBacks.turn1.rotation = 90.0;
				this.fieldCallBacks.forward2.millisToRun = millisForInches(22);
				this.fieldCallBacks.turn2.rotation = -90.0;
				this.fieldCallBacks.forward3.millisToRun = millisForInches(84);
			}
			else if (fieldPosition == 2) {
				// OK, not tested
				this.fieldCallBacks.forward1.millisToRun = millisForInches(84);
				this.fieldCallBacks.turn1.rotation = -90.0;
				this.fieldCallBacks.forward2.millisToRun = millisForInches(84);
				this.fieldCallBacks.turn2.rotation = 90.0;
				this.fieldCallBacks.forward3.millisToRun = millisForInches(84);
			
			}
			else if (fieldPosition == 3)
			{
				this.fieldCallBacks.forward1.millisToRun = millisForInches(84);
				this.fieldCallBacks.turn1.rotation = -90.0;
				this.fieldCallBacks.forward2.millisToRun = millisForInches(140);
				this.fieldCallBacks.turn2.rotation = 90.0;
				this.fieldCallBacks.forward3.millisToRun = millisForInches(84);
			
			}
		}
		else {
			if (fieldPosition == 1) {
				this.fieldCallBacks.forward1.millisToRun = millisForInches(84);
				this.fieldCallBacks.turn1.rotation = 90.0;
				this.fieldCallBacks.forward2.millisToRun = millisForInches(140);
				this.fieldCallBacks.turn2.rotation = -90.0;
				this.fieldCallBacks.forward3.millisToRun = millisForInches(84);
			}
			else if (fieldPosition == 2) {
				// OK, not tested
				this.fieldCallBacks.forward1.millisToRun = millisForInches(84);
				this.fieldCallBacks.turn1.rotation = 90.0;
				this.fieldCallBacks.forward2.millisToRun = millisForInches(22);
				this.fieldCallBacks.turn2.rotation = -90.0;
				this.fieldCallBacks.forward3.millisToRun = millisForInches(84);
		
			}
			else if (fieldPosition == 3)
			{
				// OK, not tested
				this.fieldCallBacks.forward1.millisToRun = millisForInches(84);
				this.fieldCallBacks.turn1.rotation = -90.0;
				this.fieldCallBacks.forward2.millisToRun = millisForInches(22);
				this.fieldCallBacks.turn2.rotation = 90.0;
				this.fieldCallBacks.forward3.millisToRun = millisForInches(84);

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
		GyroUtil.getInstance().run();

		if (!gameSpecificMessage.equals(this.gameSpecificMessage)) {
			this.gameSpecificMessage = gameSpecificMessage;
		}
		if (gameSpecificMessage != null) {
			if (gameSpecificMessage.length() == 3) {
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
		SmartDashboard.putNumber("Driver Station number ", DriverStation.getInstance().getLocation());
		fieldCallBacks.startingPosition = DriverStation.getInstance().getLocation();
		SmartDashboard.putString("Color Side ", gameSpecificMessage);
		SmartDashboard.putString("Side 0 ", "" + switchSides[0]);
		fieldCallBacks.isLeftMine = (switchSides[0] == SwitchSide.LEFT);
		}
		RobotMap.controllerMappings();
		switchPlaceConfig(fieldCallBacks.startingPosition, fieldCallBacks.isLeftMine);
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
		LED.colorCycle();
		Scheduler.getInstance().run();
		GyroUtil.getInstance().run();

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
		SmartDashboard.putNumber("UltraSonicDistance", UltraSonicDistance.getInstance().getDistance());
	}

	@Override
	public void testInit() {
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