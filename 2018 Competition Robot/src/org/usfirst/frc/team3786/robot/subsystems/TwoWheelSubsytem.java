package org.usfirst.frc.team3786.robot.subsystems;

public class TwoWheelSubsytem implements Drive {
	private WPI_TalonSRX left;
	private WPI_TalonSRX right;
	public TwoWheelSubsystem() {
		left = new WPI_TalonSRX(1);
		right = new WPI_TalonSRX(2);
		
	}
	@Override
	public void setMotorSpeeds(double leftFrontSpeed, double leftBackSpeed, double rightBackSpeed, double rightFrontSpeed) {
		left.set(leftFrontSpeed);
		right.set(rightFrontSpeed);
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void setBrakeMode(boolean isBraking) {
		NeutralMode mode;
		if(isBraking) {
			mode = NeutralMode.Brake;
		} else {
			mode = NeutralMode.Coast;
		}
		left.setNeutralMode(mode);
		right.setNeutralMode(mode);
		
		
		
	}

	@Override
	public void setTwoMotorSpeeds(double leftSpeed, double rightSpeed) {
		left.set(leftSpeed);
		right.set(rightSpeed);
		// TODO Auto-generated method stub
		
	}

}
