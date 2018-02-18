package org.usfirst.frc.team2500.autonomous.SubCommands;

import org.usfirst.frc.team2500.subSystems.chassis.Chassis;

import edu.wpi.first.wpilibj.command.Command;

public class DriveDistSubCommand extends Command {
	
	private double leftDistance;
	private double rightDistance;
	
	private double ERROR = 1;
	
	private boolean finnished = false;
	
	public DriveDistSubCommand(double leftDistance, double rightDistance){
		requires(Chassis.getInstance());
		this.leftDistance = leftDistance;
		this.rightDistance = rightDistance;
		finnished = false;
	}
	
	public void execute(){
		MoveTo(leftDistance, rightDistance, ERROR);
	}
	

	private static long lastTimeDistance;
	private static double p = 1, i = 1, d = 1;

	private static double errSumLeftDistance, lastErrLeftDistance;
	private static double errSumRightDistance, lastErrRightDistance;
	private void MoveTo(double leftDistance,double rightDistance, double returnError)
	{
		/*How long since we last calculated*/
		long now = System.currentTimeMillis();
		double timeChange = now - lastTimeDistance;
	  
	   	double eCodeReading = Chassis.getInstance().getLeftRate();
	   	/*Compute all the working error variables*/
	   	double errorLeft = leftDistance - eCodeReading;
	   	double dErr = 0;
		errSumLeftDistance += (errorLeft * timeChange);
		dErr = (errorLeft - lastErrLeftDistance) / timeChange;
		
		lastErrLeftDistance = errorLeft;
	  
		/*Compute PID Output*/
		double leftPower = p * errorLeft + i * errSumLeftDistance + d * dErr;
	   
		eCodeReading = Chassis.getInstance().getRightRate();
		/*Compute all the working error variables*/
		double errorRight = rightDistance - eCodeReading;
		dErr = 0;
		errSumRightDistance += (errorRight * timeChange);
		dErr = (errorRight - lastErrRightDistance) / timeChange;
	  
		/*Remember some variables for next time*/
		lastErrRightDistance = errorRight;
		lastTimeDistance = now;

		double rightPower = p * errorRight + i * errSumRightDistance + d * dErr;
	   
		Chassis.getInstance().ChangePower(leftPower, rightPower);
	   
		finnished = returnError > errorRight && returnError > errorLeft;
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return finnished;
	}
}
