package org.usfirst.frc.team3786.robot.subsystems;

import org.usfirst.frc.team3786.robot.util.ExtendedMecanumDrive;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;


/**
 *
 */
public class WheelsSubsystem extends Subsystem implements Drive{ 

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
	
	private ExtendedMecanumDrive mecanumDrive;
	
	public WheelsSubsystem() {
		leftFront = new WPI_TalonSRX(0);
		leftBack = new WPI_TalonSRX(0);
		rightBack = new WPI_TalonSRX(0);
		rightFront = new WPI_TalonSRX(0);
		mecanumDrive = (ExtendedMecanumDrive) new MecanumDrive(leftFront, leftBack, rightFront, rightBack);
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
    }
 
    public void setDirectionSpeed(double angle, double speed, double GyroAngle) {
    mecanumDrive.drivePolar(speed, angle, 0, GyroAngle);
    }
}

