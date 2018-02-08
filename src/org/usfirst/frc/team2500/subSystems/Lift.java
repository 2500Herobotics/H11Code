package org.usfirst.frc.team2500.subSystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class Lift extends PIDSubsystem {

	private final static double P = 1;
	private final static double I = 1;
	private final static double D = 1;

	private final int ENCODER_PORT1 = 4;
	private final int ENCODER_PORT2 = 5;
	
	private final int LIFT_MOTOR_PORT = 6;
	
	private final double TARGET_ERROR =  1;
	
	private Victor lift_motor;
	private Encoder encoder;
	
	public Lift() {
		super("Lift",P, I, D);
		setAbsoluteTolerance(0.05);
		getPIDController().setContinuous(false);
		lift_motor = new Victor(LIFT_MOTOR_PORT);
		encoder = new Encoder(ENCODER_PORT1, ENCODER_PORT2);
	}

	@Override
	protected double returnPIDInput() {
		return encoder.getRate();
	}

	@Override
	protected void usePIDOutput(double output) {
		lift_motor.pidWrite(output);
	}

	@Override
	protected void initDefaultCommand() {
		
	}
	
	public void setTarget(double target){
		getPIDController().setSetpoint(target);
	}
	
	public void stop(){
		getPIDController().disable();
	}
	
	public void start(){
		getPIDController().enable();
	}
}