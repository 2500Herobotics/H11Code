package org.usfirst.frc.team2500.subSystems.chassis;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;

public class DriveDist extends PIDCommand {

	private final static double P = 1.0;
	private final static double I = 0.0;
	private final static double D = 0.1;
	
	private double distance;
	
	public DriveDist(double distance){
        super("Drive",P, I, D);
		requires(Chassis.getInstance());
		
		getPIDController().setContinuous(false);
		getPIDController().setAbsoluteTolerance(0.1);
        setTimeout(2);

		this.distance = distance;
	}
	
	public void initialize(){
		//Make sure everything is 0 ed properly
		Chassis.getInstance().resetEncoder();
		Chassis.getInstance().resetGyro();

		System.out.println("Driving to: " + distance);
		getPIDController().setSetpoint(distance);
		
	}

	@Override
	protected double returnPIDInput() {
//		System.out.println(Chassis.getInstance().getAverageDistance());
		return Chassis.getInstance().getAverageDistance();
	}

	@Override
	protected void usePIDOutput(double output) {
		Chassis.getInstance().arcadeDrive(output * 0.4, Chassis.getInstance().getRotation() * -0.1);
	}
    
    protected boolean isFinished() {
        return isTimedOut() || getPIDController().onTarget();
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	end();
    }
}
