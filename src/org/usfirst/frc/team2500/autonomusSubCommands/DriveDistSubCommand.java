package org.usfirst.frc.team2500.autonomusSubCommands;

import org.usfirst.frc.team2500.robot.Chassis;

public class DriveDistSubCommand implements AutoSubCommand {
	
	private double leftDistance;
	private double rightDistance;
	
	private double ERROR = 1;
	
	public DriveDistSubCommand(double leftDistance, double rightDistance){
		this.leftDistance = leftDistance;
		this.rightDistance = rightDistance;
	}
	
	public boolean run(){
		return MoveTo(leftDistance, rightDistance, ERROR);
	}
	

	private static long lastTimeDistance;
	private static double p = 1, i = 1, d = 1;

	private static double errSumLeftDistance, lastErrLeftDistance;
	private static double errSumRightDistance, lastErrRightDistance;
	private boolean MoveTo(double leftDistance,double rightDistance, double returnError)
	{
		/*How long since we last calculated*/
		long now = System.currentTimeMillis();
		double timeChange = now - lastTimeDistance;
	  
	   	double eCodeReading = Chassis.getLeftRate();
	   	/*Compute all the working error variables*/
	   	double errorLeft = leftDistance - eCodeReading;
	   	double dErr = 0;
		errSumLeftDistance += (errorLeft * timeChange);
		dErr = (errorLeft - lastErrLeftDistance) / timeChange;
		
		lastErrLeftDistance = errorLeft;
	  
		/*Compute PID Output*/
		double leftPower = p * errorLeft + i * errSumLeftDistance + d * dErr;
	   
		eCodeReading = Chassis.getRightRate();
		/*Compute all the working error variables*/
		double errorRight = rightDistance - eCodeReading;
		dErr = 0;
		errSumRightDistance += (errorRight * timeChange);
		dErr = (errorRight - lastErrRightDistance) / timeChange;
	  
		/*Remember some variables for next time*/
		lastErrRightDistance = errorRight;
		lastTimeDistance = now;

		double rightPower = p * errorRight + i * errSumRightDistance + d * dErr;
	   
	   Chassis.ChangePower(leftPower, rightPower);
	   
	   return returnError > errorRight && returnError > errorLeft;
	}
}
