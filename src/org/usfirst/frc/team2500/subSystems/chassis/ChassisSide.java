package org.usfirst.frc.team2500.subSystems.chassis;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.InternalButton;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
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
		setAbsoluteTolerance(0.05);
		getPIDController().setContinuous(false);
		this.motor = new Talon(motor);

		double pulceRate = 250/13208.5;
		
		encoder = new Encoder(e1,e2);
		encoder.setDistancePerPulse(pulceRate);
		encoder.reset();
		
		start();
	}

	@Override
	protected double returnPIDInput() {
		return encoder.getDistance();
	}

	@Override
	protected void usePIDOutput(double output) {
		motor.pidWrite(output);
	}

	@Override
	protected void initDefaultCommand() {
		
	}
	public void stop(){
		getPIDController().disable();
	}
	
	public void start(){
		getPIDController().enable();
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
		return encoder.getDistance();
	}
}