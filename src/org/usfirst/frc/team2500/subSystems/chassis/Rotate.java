package org.usfirst.frc.team2500.subSystems.chassis;

import edu.wpi.first.wpilibj.command.PIDCommand;

public class Rotate extends PIDCommand{
	private final static double P = 1;
	private final static double I = 1;
	private final static double D = 1;
	
	private double degrees;
	
    public Rotate(double degrees) {
        super("Rotate",P, I, D);
        
        this.degrees = degrees;
        
        requires(Chassis.getInstance());
        Chassis.getInstance().stopPID();
        
		getPIDController().setContinuous(false);

        setTimeout(2);
    }
	
	public void initialize(){
        
		getPIDController().setSetpoint(degrees);
		start();
	}

	@Override
	protected double returnPIDInput() {
		return Chassis.getInstance().getRotation();
	}

	@Override
	protected void usePIDOutput(double output) {
		Chassis.getInstance().arcadeDrive(0, output);
	}
    
    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	end();
    }
}
