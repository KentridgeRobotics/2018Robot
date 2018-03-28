package org.usfirst.frc.team3786.robot.commands.auto;

public class FieldCallBacks {
	public int startingPosition;
	public boolean isLeftMine; 
	boolean shouldGoLeft;
	double sideWaysTime;
	public final DriveForwardParameters forward1; 
	public final RotationParameters turn1;
	public final DriveForwardParameters forward2;
	public final RotationParameters turn2;
	public final DriveForwardParameters forward3;
	public final RotationParameters turn3;
	public FieldCallBacks(){
		forward1 = new DriveForwardParameters();
		turn1 = new RotationParameters(); 
		forward2 = new DriveForwardParameters();
		turn2 = new RotationParameters();
        forward3 = new DriveForwardParameters();
		turn3 = new RotationParameters();
	}
}
