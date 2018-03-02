package org.usfirst.frc.team2500.subSystems.chassis;
  
 import org.usfirst.frc.team2500.robot.RobotPorts;
  
 import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SPI;
 import edu.wpi.first.wpilibj.Solenoid;
 import edu.wpi.first.wpilibj.command.Subsystem;

public class Chassis extends Subsystem{

	//Making only one chassis exist in the code
	public static Chassis instance;
	
	public static Chassis getInstance()
    {
		if (instance == null)
		   instance = new Chassis();
	
		return instance;
    }

	//Each side of the chassis is its own pid subsystem to make drive dist easyer
	private ChassisSide leftChassis;
	private ChassisSide rightChassis;
	
	private Solenoid shifter;

	//What we should be tring to shift to
	public boolean shiftTarget;

	private AHRS gyro;
	
	public Chassis(){
		leftChassis = new ChassisSide(RobotPorts.LEFT_DRIVE_PORT,RobotPorts.LEFT_ENCODER_PORT1,RobotPorts.LEFT_ENCODER_PORT2);
		rightChassis = new ChassisSide(RobotPorts.RIGHT_DRIVE_PORT,RobotPorts.RIGHT_ENCODER_PORT1,RobotPorts.RIGHT_ENCODER_PORT2);

		gyro = new AHRS(SPI.Port.kMXP);
		gyro.reset();

		shiftTarget = false;
	}
	
	//Will always be using arcade drive when nothing else is using it
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ArcadeDrive());
	}
	
	public void resetEncoder(){
		leftChassis.resetEncoder();
		rightChassis.resetEncoder();
	}
	
	//Set the target distence to drive to
	public void setDistance(double left,double right){
		leftChassis.setTarget(left);
		rightChassis.setTarget(right * -1);
	}
	
	public void printDistace(){
		System.out.println(leftChassis.getDistance() + "   " + rightChassis.getDistance());
	}
	
	public double getAverageRate(){
		return (leftChassis.getRate() + rightChassis.getRate())/2;
	}
	
	public double getLeftRate(){
		return leftChassis.getRate();
	}
	
	public double getRightRate(){
		return -rightChassis.getRate();
	}
	
	public double getRotation(){
		return gyro.getAngle();
	}

	//Auto shifting
	private final double MAX_HIGH_GEAR_SPEED = 2;
	private final double MAX_LOW_GEAR_SPEED = 1;

	//Magic number used to make highgear autoshift smooth
	//It assumes that the acceleration is linear and makes the low gear max speed match up with that same value on the highgear equation
	private final double MIN_MAX_CONVERTER = 1/(MAX_LOW_GEAR_SPEED/MAX_HIGH_GEAR_SPEED);

	//What persenct of maxspeed do we shift at because we usualy dont get all the way up
	private final double LOW_GEAR_SHIFT_PERCENT_HIGH = 0.9;
	//Switch back a bit lower then the switch up to stop rapid toggle between the two
	private final double LOW_GEAR_SHIFT_PERCENT_LOW = 0.8;

	public void shiftingTankDrive(double left,double right){
		if(shiftTarget){
			double averageRate = Math.abs(getAverageRate());

			if(averageRate > MAX_LOW_GEAR_SPEED * LOW_GEAR_SHIFT_PERCENT_HIGH){
				shifter.set(true);
				tankDrive(left,right);
				return;
			}
			if(averageRate < MAX_LOW_GEAR_SPEED * LOW_GEAR_SHIFT_PERCENT_LOW){
				shifter.set(false);
			}

			tankDrive(left*MIN_MAX_CONVERTER,right*MIN_MAX_CONVERTER);
		}
		else{
			tankDrive(left,right);
		}
	}


	public void tankDrive(double left,double right){
		leftChassis.setSpeed(left);
		rightChassis.setSpeed(right);
	}

	public void arcadeDrive(double throttle, double turn) {
	    arcadeDrive(throttle, turn, true);
	}
	
	public void arcadeDrive(double throttle,double turn, boolean square){

	    double leftMotorOutput;
	    double rightMotorOutput;

	    double maxInput = Math.copySign(Math.max(Math.abs(throttle), Math.abs(turn)), throttle);

	    if (throttle >= 0.0) {
	      // First quadrant, else second quadrant
	      if (turn >= 0.0) {
	        leftMotorOutput = maxInput;
	        rightMotorOutput = throttle - turn;
	      } else {
	        leftMotorOutput = throttle + turn;
	        rightMotorOutput = maxInput;
	      }
	    } else {
	      // Third quadrant, else fourth quadrant
	      if (turn >= 0.0) {
	        leftMotorOutput = throttle + turn;
	        rightMotorOutput = maxInput;
	      } else {
	        leftMotorOutput = maxInput;
	        rightMotorOutput = throttle - turn;
	      }
	    }
	    
	    shiftingTankDrive(leftMotorOutput,rightMotorOutput * -1);
	}
	
	public void stopPID(){
		leftChassis.stop();
		rightChassis.stop();
	}
	
	public void startPID(){
		leftChassis.start();
		rightChassis.start();
	}

	public void resetGyro() {
		gyro.reset();
	}

	public PIDController getPIDController() {
		return getPIDController();
	}
}
