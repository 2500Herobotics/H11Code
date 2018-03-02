package org.usfirst.frc.team2500.subSystems.chassis;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class ChassisSide extends PIDSubsystem {

	private final static double P = 0.01;
	private final static double I = 1;
	private final static double D = 0.1;

	Talon motor;
	
	Encoder encoder;

	public int targetFloor;
	
	public ChassisSide(int motor,int e1, int e2) {
		super("Chassis Side",P, I, D);
		getPIDController().setContinuous(false);
		this.motor = new Talon(motor);

		//This is the magic number that makes one number 1 inch on our wheels
		double pulceRate = 250/13208.5;
		
		//Make encders and reset 0 them
		encoder = new Encoder(e1,e2);
		encoder.setDistancePerPulse(pulceRate);
		encoder.reset();
		
		start();
	}

	@Override
	protected double returnPIDInput() {
		return encoder.getDistance();
	}

	int encoderTimeout = 0;
	//Lock pid for dist if encoders arnt getting reading
	boolean pidLock = false;
	
	@Override
	protected void usePIDOutput(double output) {
		motor.pidWrite(output * 0.3);
		
		//If we dont get a change on the encoders for a long time and we should be stop the drive
		if(output > 0 && encoder.getRate() == 0){
			encoderTimeout++;
			if(encoderTimeout > 100){
				pidLock = true;
				Chassis.getInstance().stopPID();
			}
		}
		else{
			encoderTimeout = 0;
		}
	}

	@Override
	protected void initDefaultCommand() {
		
	}
	public void stop(){
		getPIDController().disable();
	}
	
	public void start(){
		if(!pidLock){
			getPIDController().enable();
		}
	}
	
	public void setTarget(double target){
		getPIDController().setSetpoint(target);
	}
	
	public void resetEncoder(){
		encoder.reset();
	}
	
	public double getDistance(){
		return encoder.getDistance();
	}
	
	public void setSpeed(double speed){
		motor.set(speed);
	}
	
	public double getRate(){
		return encoder.getRate();
	}
}