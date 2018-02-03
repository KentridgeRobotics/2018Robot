package org.usfirst.frc.team3786.robot.subsystems;

import org.usfirst.frc.team3786.robot.Robot;
import org.usfirst.frc.team3786.robot.util.GyroUtil;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;


/**
 *
 */
public class WheelsSubsystem extends Subsystem implements Drive { 

    public static WheelsSubsystem instance;
    
    public static WheelsSubsystem getInstance() {
    	if(instance == null)
    		instance = new WheelsSubsystem();
    	return instance;
    }
	
	private WPI_TalonSRX leftFront;
	private WPI_TalonSRX leftBack;
	private WPI_TalonSRX rightBack;
	private WPI_TalonSRX rightFront;
	
	private MecanumDrive mecanumDrive;
	
	public WheelsSubsystem() {
		leftFront = new WPI_TalonSRX(2); // Yellow
		leftBack = new WPI_TalonSRX(3); // Purple
		rightBack = new WPI_TalonSRX(1); // Blue
		rightFront = new WPI_TalonSRX(4); // Orange
		mecanumDrive = new MecanumDrive(leftFront, leftBack, rightFront, rightBack);
	}
@Override
	public void setMotorSpeeds(double leftFrontSpeed, double leftBackSpeed, double rightBackSpeed, double rightFrontSpeed) {
		leftFront.set(leftFrontSpeed);
		leftBack.set(leftBackSpeed);
		rightBack.set(rightBackSpeed);
		rightFront.set(rightFrontSpeed);
		
	} 
@Override
public void setTwoMotorSpeeds(double leftSpeed, double rightSpeed) {
	leftFront.set(leftSpeed);
	leftBack.set(leftSpeed);
	rightBack.set(rightSpeed);
	rightFront.set(rightSpeed);
	
} 
@Override
	public void setBrakeMode(boolean isBraking) {
		NeutralMode mode;
		if(isBraking) {
			mode = NeutralMode.Brake;
		} else {
			mode = NeutralMode.Coast;
		}
		leftFront.setNeutralMode(mode);
		leftBack.setNeutralMode(mode);
		rightBack.setNeutralMode(mode);
		rightFront.setNeutralMode(mode);
	}
	
    public void initDefaultCommand() {
        //Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	leftFront.configOpenloopRamp(0.2, 0);
		leftBack.configOpenloopRamp(0.2, 0);
		rightBack.configOpenloopRamp(0.2, 0);
		rightFront.configOpenloopRamp(0.2, 0);
    }
 
    public void setDirectionSpeed(double angle, double speed, double GyroAngle) {
    	mecanumDrive.drivePolar(speed, angle, 0);
    }
    
    public void setXboxDrive(double x, double y) {
    	double heading = GyroUtil.getInstance().getHeading();
    	mecanumDrive.driveCartesian(x, y, 0, heading);
    }
    
}

