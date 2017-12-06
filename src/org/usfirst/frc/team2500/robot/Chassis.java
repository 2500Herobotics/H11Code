package org.usfirst.frc.team2500.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class Chassis {

	private Encoder leftSideEndoder;
	private Encoder rightSideEndoder;
	
	private Gyro gyro;

	private Victor leftSideMotor1;
	private Victor leftSideMotor2;
	private Victor leftSideMotor3;

	private Victor rightSideMotor1;
	private Victor rightSideMotor2;
	private Victor rightSideMotor3;
	
	public Chassis(){
		leftSideMotor1 = new Victor(0);
		leftSideMotor2 = new Victor(1);
		leftSideMotor3 = new Victor(2);

		rightSideMotor1 = new Victor(3);
		rightSideMotor2 = new Victor(4);
		rightSideMotor3 = new Victor(5);

		leftSideEndoder = new Encoder(0,1);
		rightSideEndoder = new Encoder(2,3);
	}
	
	/**
	 * 
	 * @param l1 left motor 1
	 * @param l2 left motor 1
	 * @param l3 left motor 1
	 * @param r1 right motor 1
	 * @param r2 right motor 1
	 * @param r3 right motor 1
	 */
	public void setMotors(int l1,int l2,int l3,int r1,int r2,int r3){
		leftSideMotor1 = new Victor(l1);
		leftSideMotor2 = new Victor(l2);
		leftSideMotor3 = new Victor(l3);
		
		rightSideMotor1 = new Victor(r1);
		rightSideMotor2 = new Victor(r2);
		rightSideMotor3 = new Victor(r3);
	}
	
	/**
	 * 
	 * @param l1 first port for the left endoder
	 * @param l2 second port for the left endoder
	 * @param r1 first port for the right endoder
	 * @param r2 second port for the right endoder
	 */
	public void setEncoders(int l1,int l2,int r1,int r2){
		leftSideEndoder = new Encoder(l1,l2);
		rightSideEndoder = new Encoder(r1,r2);
	}
	
	
	
	/*working variables*/
	long lastTime;
	double dkp = 1, dki = 1, dkd = 1;
	
	double errSum, lastErr;
	double lastTarget;
	public void driveDist(double dist)
	{
	   /*How long since we last calculated*/
	   long now = System.currentTimeMillis();
	   double timeChange = (double)(now - lastTime);
	  
	   double eCodeReading = (leftSideEndoder.getDistance() + rightSideEndoder.getDistance())/2;
	   /*Compute all the working error variables*/
	   double error = dist - eCodeReading;
	   double dErr = 0;
	   if(lastTarget == dist){
		   errSum += (error * timeChange);
		   dErr = (error - lastErr) / timeChange;
	   }
	   else{
		   errSum = 0;
	   }
	  
	   /*Compute PID Output*/
	   double output = dkp * error + dki * errSum + dkd * dErr;
	  
	   /*Remember some variables for next time*/
	   lastErr = error;
	   lastTime = now;
	   
	   tankDrive(output, output);
	}

	double errSumLeft, errSumRight, lastErrLeft, lastErrRight;
	double lastTargetLeft, lastTargetRight;
	public void driveDist(double rightDist, double leftDist){
		   /*How long since we last calculated*/
		   long now = System.currentTimeMillis();
		   double timeChange = (double)(now - lastTime);
		  
		   double eCodeReading = leftSideEndoder.getDistance();
		   /*Compute all the working error variables*/
		   double error = leftDist - eCodeReading;
		   double dErr = 0;
		   if(lastTargetLeft == leftDist){
			   errSumLeft += (error * timeChange);
			   dErr = (error - lastErrLeft) / timeChange;
		   }
		   else{
			   errSumLeft = 0;
		   }
		  
		   /*Compute PID Output*/
		   double outputLeft = dkp * error + dki * errSumLeft + dkd * dErr;
		  
		   /*Remember some variables for next time*/
		   lastErrLeft = error;

		   eCodeReading = rightSideEndoder.getDistance();
		   /*Compute all the working error variables*/
		   error = rightDist - eCodeReading;
		   dErr = 0;
		   if(lastTargetRight == rightDist){
			   errSumRight += (error * timeChange);
			   dErr = (error - lastErrRight) / timeChange;
		   }
		   else{
			   errSumRight = 0;
		   }
		  
		   /*Compute PID Output*/
		   double outputRight = dkp * error + dki * errSumRight + dkd * dErr;
		  
		   /*Remember some variables for next time*/
		   lastErrRight = error;
		   
		   
		   lastTime = now;
		   
		   tankDrive(outputLeft, outputRight);
	}
	  
	void setDistanceK(double dKp, double dKi, double dKd)
	{
	   dkp = dKp;
	   dki = dKi;
	   dkd = dKd;
	}
	

	double rkp = 1, rki = 1, rkd = 1;
	
	long lastTimeRotation;
	
	double errSumRotation, lastErrRotation;
	double lastTargetRotation;
	/**
	 * This is in degrees
	 */
	void rotateTo(double degrees){

		   /*How long since we last calculated*/
		   long now = System.currentTimeMillis();
		   double timeChange = (double)(now - lastTime);
		  
		   double gyroReading = gyro.getAngle();
		   /*Compute all the working error variables*/
		   double error = degrees - gyroReading;
		   double Err = 0;
		   if(lastTargetRotation == gyroReading){
			   errSumRotation += (error * timeChange);
			   Err = (error - lastErrRotation) / timeChange;
		   }
		   else{
			   errSumRotation = 0;
		   }
		  
		   /*Compute PID Output*/
		   double output = rkp * error + rki * errSumRotation + rkd * Err;
		  
		   /*Remember some variables for next time*/
		   lastErrRotation = error;
		   lastTimeRotation = now;
		   
		   tankDrive(output, output * -1);
	}
	
	public void tankDrive(double leftSpeed, double rightSpeed){
		leftSideMotor1.set(leftSpeed);
		leftSideMotor2.set(leftSpeed);
		leftSideMotor3.set(leftSpeed);

		rightSideMotor1.set(rightSpeed);
		rightSideMotor2.set(rightSpeed);
		rightSideMotor3.set(rightSpeed);
	}
}
