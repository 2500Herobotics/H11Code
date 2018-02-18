package org.usfirst.frc.team2500.autonomous.SubCommands;

import org.usfirst.frc.team2500.subSystems.chassis.Chassis;

import edu.wpi.first.wpilibj.command.Command;

public class RotateSubCommand extends Command {
	
	double degrees;
	
	double ERROR = 1;
	
	boolean finished = false;
	
	public RotateSubCommand(double degrees){
		requires(Chassis.getInstance());
		this.degrees = degrees;
		finished = false;
	}
	
	public void execute(){
		rotateTo(degrees,ERROR);
	}
	
	private static double p = 1, i = 1, d = 1;
	
	private static long lastTimeRotation;
	
	private static double errSumRotation, lastErrRotation;
	private static double lastTargetRotation;
	/**
	 * This is in degrees
	 */
	public void rotateTo(double degrees, double errorMax){

		   /*How long since we last calculated*/
		   long now = System.currentTimeMillis();
		   double timeChange = (double)(now - lastTimeRotation);
		  
		   double gyroReading = Chassis.getInstance().getAngle();
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
		   
		   Chassis.getInstance().ChangePower(output, output * -1);
		   finished =  error < errorMax;
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return finished;
	}
}
