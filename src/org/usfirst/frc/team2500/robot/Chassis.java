package org.usfirst.frc.team2500.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;

public class Chassis {

	private static final int LEFT_ENCODER_PORT1 = 0;
	private static final int LEFT_ENCODER_PORT2 = 1;
	
	private static final int RIGHT_ENCODER_PORT1 = 2;
	private static final int RIGHT_ENCODER_PORT2 = 3;
	
	private static Encoder leftSideEndoder, rightSideEndoder;
	
	private static AHRS gyro;

	private static final int LEFT_MOTOR_PORT1 = 6;
	private static final int LEFT_MOTOR_PORT2 = 7;

	private static final int RIGHT_MOTOR_PORT1 = 8;
	private static final int RIGHT_MOTOR_PORT2 = 9;

	private static Victor leftSideMotor1,leftSideMotor2;

	private static Victor rightSideMotor1,rightSideMotor2;
	
	private static final int SHIFTER_PORT = 0;
	
	private static Solenoid shifter;
	
	private static final double MAX_HIGH_GEAR_SPEED = 0;
	private static final double MAX_LOW_GEAR_SPEED = 0;
	
	private static double maxSpeed;
	
	public static void initialize(){

//		leftSideEndoder = new Encoder(LEFT_ENCODER_PORT1,LEFT_ENCODER_PORT2);
//		rightSideEndoder = new Encoder(RIGHT_ENCODER_PORT1,RIGHT_ENCODER_PORT2);
//		leftSideEndoder.setDistancePerPulse(1);
//		leftSideEndoder.reset();
//		rightSideEndoder.setDistancePerPulse(1);
//		rightSideEndoder.reset();
		
		gyro = new AHRS(SPI.Port.kMXP);
		gyro.reset();
		
		leftSideMotor1 = new Victor(LEFT_MOTOR_PORT1);
		leftSideMotor2 = new Victor(LEFT_MOTOR_PORT2);

		rightSideMotor1 = new Victor(RIGHT_MOTOR_PORT1);
		rightSideMotor2 = new Victor(RIGHT_MOTOR_PORT2);
		
		shifter = new Solenoid(SHIFTER_PORT);
		
		maxSpeed = MAX_LOW_GEAR_SPEED;
	}

	// call to change the power given to the motor
	public static void ChangePower(double powerL,double powerR){
		leftSideMotor1.set(powerL);
		leftSideMotor2.set(powerL);
		//leftSideMotor3.set(powerL);
		
		rightSideMotor1.set(powerR);
		rightSideMotor2.set(powerR);
		//rightSideMotor3.set(powerR);
	}
	
	public static void shift(boolean setShift){
		shifter.set(setShift);
		maxSpeed = (setShift)? MAX_LOW_GEAR_SPEED : MAX_HIGH_GEAR_SPEED;
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
	
	private static long lastTimeSpeed;
	private static double skp = 1, ski = 1, skd = 1;

	private static double errSumLeftSpeed, lastErrLeftSpeed;
	private static double errSumRightSpeed, lastErrRightSpeed;
	public static void ChangeSpeed(double leftSpeed, double rightSpeed)
	{
		/*How long since we last calculated*/
		long now = System.currentTimeMillis();
		double timeChange = (double)(now - lastTimeSpeed);
	  
	   	double eCodeReading = leftSideEndoder.getRate();
	   	/*Compute all the working error variables*/
	   	double error = getSpeedError(eCodeReading,leftSpeed);
	   	double dErr = 0;
		errSumLeftSpeed += (error * timeChange);
		dErr = (error - lastErrLeftSpeed) / timeChange;
		
		lastErrLeftSpeed = error;
	  
		/*Compute PID Output*/
		double leftPower = skp * error + ski * errSumLeftSpeed + skd * dErr;
	   
		eCodeReading = rightSideEndoder.getRate();
		/*Compute all the working error variables*/
		error = getSpeedError(eCodeReading,rightSpeed);
		dErr = 0;
		errSumRightSpeed += (error * timeChange);
		dErr = (error - lastErrRightSpeed) / timeChange;
	  
		/*Remember some variables for next time*/
		lastErrRightSpeed = error;
		lastTimeSpeed = now;

		double rightPower = skp * error + ski * errSumRightSpeed + skd * dErr;
	   
	   ChangePower(leftPower, rightPower);
	}
	
	private static double getSpeedError(double currentSpeed, double targetSpeed){
		return (currentSpeed-(targetSpeed * maxSpeed))/maxSpeed;
	}
	
	public static double getLeftRate(){
		return leftSideEndoder.getRate();
	}
	
	public static double getRightRate(){
		return rightSideEndoder.getRate();
	}
	
	public static double getLeftDist(){
		return leftSideEndoder.getDistance();
	}
	
	public static double getRightDist(){
		return rightSideEndoder.getDistance();
	}
	
	public static double getAngle(){
		return gyro.getAngle();
	}
	
	public static void resetEncoders(){
		leftSideEndoder.reset();
		rightSideEndoder.reset();
	}
	
	public static void resetGyro(){
		gyro.reset();
	}
}
