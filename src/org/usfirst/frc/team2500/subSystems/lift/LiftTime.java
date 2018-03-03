package org.usfirst.frc.team2500.subSystems.lift;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;

public class LiftTime extends Command{
	
    public LiftTime(double time) {
    	requires(Lift.getInstance());
        setTimeout(time);
    }
	
	public void initialize(){
		System.out.println("Lifting block");
	}
    
	protected void execute(){
		Lift.getInstance().setSpeed(-0.8);
	}
	
    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
    	Lift.getInstance().setSpeed(0);
    }

    protected void interrupted() {
    	end();
    }
}
