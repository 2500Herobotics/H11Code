package org.usfirst.frc.team2500.subSystems.chassis;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ChassisSide extends Subsystem {

	Talon motor;
	
	Encoder encoder;

	public int targetFloor;
	
	public ChassisSide(int motor,int e1, int e2) {
		this.motor = new Talon(motor);

		//This is the magic number that makes one number 1 inch on our wheels
		//push bot 250in and devide by the dist at pulce rate one
		double pulceRate = 250/13208.5;
		
		//Make encders and reset 0 them
		encoder = new Encoder(e1,e2);
		encoder.setDistancePerPulse(pulceRate);
		encoder.reset();
	}

	@Override
	protected void initDefaultCommand() {
		
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