package org.usfirst.frc.team2500.subSystems.chassis;

import edu.wpi.first.wpilibj.command.PIDCommand;

public class Rotate extends PIDCommand{
	private final static double P = 0.1;
	private final static double I = 0.8;
	private final static double D = 0.1;
	
	private double degrees;
	
    public Rotate(double degrees) {
        super("Rotate",P, I, D);
        
        this.degrees = degrees;
        
        requires(Chassis.getInstance());
        
		getPIDController().setContinuous(false);
//		getPIDController().setAbsoluteTolerance(5);
		
        setTimeout(6);
    }
	
	public void initialize(){
		Chassis.getInstance().stopPID();
		System.out.println("Rotating to: " + degrees);
		Chassis.getInstance().resetGyro();
		getPIDController().setSetpoint(degrees);
	}

	@Override
	protected double returnPIDInput() {
		System.out.println(Chassis.getInstance().getRotation());
		return Chassis.getInstance().getRotation();
	}

	@Override
	protected void usePIDOutput(double output) {
		Chassis.getInstance().arcadeDrive(0, output * 0.1);
	}
    
    protected boolean isFinished() {
        return isTimedOut();//|| getPIDController().onTarget();
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	end();
    }
}
