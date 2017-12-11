package org.usfirst.frc.team2500.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;

public class Chassis {

	private static final int LEFT_ENCODER_PORT1 = 0;
	private static final int LEFT_ENCODER_PORT2 = 1;
	
	private static final int RIGHT_ENCODER_PORT1 = 0;
	private static final int RIGHT_ENCODER_PORT2 = 1;
	
	private static Encoder leftSideEndoder, rightSideEndoder;
	
	private static ADXRS450_Gyro gyro;

	private static final int LEFT_MOTOR_PORT1 = 0;
	private static final int LEFT_MOTOR_PORT2 = 1;
	private static final int LEFT_MOTOR_PORT3 = 2;

	private static final int RIGHT_MOTOR_PORT1 = 3;
	private static final int RIGHT_MOTOR_PORT2 = 4;
	private static final int RIGHT_MOTOR_PORT3 = 5;

	private static Victor leftSideMotor1,leftSideMotor2,leftSideMotor3;

	private static Victor rightSideMotor1,rightSideMotor2,rightSideMotor3;
	
	public static void initialize(){
		
		leftSideMotor1 = new Victor(LEFT_MOTOR_PORT1);
		leftSideMotor2 = new Victor(LEFT_MOTOR_PORT2);
		leftSideMotor3 = new Victor(LEFT_MOTOR_PORT3);

		rightSideMotor1 = new Victor(RIGHT_MOTOR_PORT1);
		rightSideMotor2 = new Victor(RIGHT_MOTOR_PORT2);
		rightSideMotor3 = new Victor(RIGHT_MOTOR_PORT3);

		leftSideEndoder = new Encoder(LEFT_ENCODER_PORT1,LEFT_ENCODER_PORT2);
		rightSideEndoder = new Encoder(RIGHT_ENCODER_PORT1,RIGHT_ENCODER_PORT2);
		
		gyro = new ADXRS450_Gyro();
	}

	// call to change the power given to the motor
	public static void ChangePower(double powerL,double powerR){
		leftSideMotor1.set(powerL);
		leftSideMotor2.set(powerL);
		leftSideMotor3.set(powerL);
		
		rightSideMotor1.set(powerR);
		rightSideMotor2.set(powerR);
		rightSideMotor3.set(powerR);
	}

	public void arcadeDrive(double turnValue, double moveValue){
		//This is the speed we want the left and the right wheels to go at when this fun
	    double leftTargetSpeed = 0.0;
	    double rightTargetSpeed = 0.0;
	    
	    //Math to convert the forword and  rotaion to left and right
	    if (turnValue > 0.0) {
	        if (moveValue > 0.0) {
	          leftTargetSpeed = turnValue - moveValue;
	          rightTargetSpeed = Math.max(turnValue, moveValue);
	        } else {
	          leftTargetSpeed = Math.max(turnValue, -moveValue);
	          rightTargetSpeed = turnValue + moveValue;
	        }
	      } else {
	        if (moveValue > 0.0) {
	          leftTargetSpeed = -Math.max(-turnValue, moveValue);
	          rightTargetSpeed = turnValue + moveValue;
	        } else {
	          leftTargetSpeed = turnValue - moveValue;
	          rightTargetSpeed = -Math.max(-turnValue, -moveValue);
	        }
	    }
	    
	    ChangePower(leftTargetSpeed, rightTargetSpeed);
	}
	
	/*working variables*/
	private static long lastTime;
	private static double kp = 1, ki = 1, kd = 1;

	private static double errSumLeft, lastErrLeft;
	private static double errSumRight, lastErrRight;
	public static void ChangeSpeed(double rate)
	{
		/*How long since we last calculated*/
		long now = System.currentTimeMillis();
		double timeChange = (double)(now - lastTime);
	  
	   	double eCodeReading = leftSideEndoder.getRate();
	   	/*Compute all the working error variables*/
	   	double error = rate - eCodeReading;
	   	double dErr = 0;
		errSumLeft += (error * timeChange);
		dErr = (error - lastErrLeft) / timeChange;
		
		lastErrLeft = error;
	  
		/*Compute PID Output*/
		double leftPower = kp * error + ki * errSumLeft + kd * dErr;
	   
		eCodeReading = rightSideEndoder.getRate();
		/*Compute all the working error variables*/
		error = rate - eCodeReading;
		dErr = 0;
		errSumRight += (error * timeChange);
		dErr = (error - lastErrRight) / timeChange;
	  
		/*Remember some variables for next time*/
		lastErrRight = error;
		lastTime = now;

		double rightPower = kp * error + ki * errSumRight + kd * dErr;
	   
	   ChangePower(leftPower, rightPower);
	}
	
	private static double rkp = 1, rki = 1, rkd = 1;
	
	private static long lastTimeRotation;
	
	private static double errSumRotation, lastErrRotation;
	private static double lastTargetRotation;
	/**
	 * This is in degrees
	 */
	public static void rotateTo(double degrees){

		   /*How long since we last calculated*/
		   long now = System.currentTimeMillis();
		   double timeChange = (double)(now - lastTimeRotation);
		  
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
		   
		   ChangePower(output, output * -1);
	}
	  
	public void setRotationK(double rKp, double rKi, double rKd)
	{
	   rkp = rKp;
	   rki = rKi;
	   rkd = rKd;
	}
	
	public static double getLeftRate(){
		return leftSideEndoder.getRate();
	}
	
	public static double getLeftDist(){
		return leftSideEndoder.getDistance();
	}
	
	public static double getRightRate(){
		return rightSideEndoder.getRate();
	}
	
	public static double getRightDist(){
		return rightSideEndoder.getDistance();
	}
}
