package org.usfirst.frc.team2500.autonomusSubCommands;

import org.usfirst.frc.team2500.robot.Chassis;

public class RotateSubCommand implements AutoSubCommand {
	
	double degrees;
	
	double ERROR = 1;
	
	public RotateSubCommand(double degrees){
		this.degrees = degrees;
	}
	
	public boolean run(){
		return rotateTo(degrees,ERROR);
	}
	
	private static double p = 1, i = 1, d = 1;
	
	private static long lastTimeRotation;
	
	private static double errSumRotation, lastErrRotation;
	private static double lastTargetRotation;
	/**
	 * This is in degrees
	 */
	public static boolean rotateTo(double degrees, double errorMax){

		   /*How long since we last calculated*/
		   long now = System.currentTimeMillis();
		   double timeChange = (double)(now - lastTimeRotation);
		  
		   double gyroReading = Chassis.getAngle();
		   /*Compute all the working error variables*/
		   double error = degrees - gyroReading;
		   double derivativeError = 0;
		   if(lastTargetRotation == gyroReading){
			   errSumRotation += (error * timeChange);
			   derivativeError = (error - lastErrRotation) / timeChange;
		   }
		   else{
			   errSumRotation = 0;
		   }
		  
		   /*Compute PID Output*/
		   double output = p * error + i * errSumRotation + d * derivativeError;
		  
		   /*Remember some variables for next time*/
		   lastErrRotation = error;
		   lastTimeRotation = now;
		   
		   Chassis.ChangePower(output, output * -1);
		   return error < errorMax;
	}
}
