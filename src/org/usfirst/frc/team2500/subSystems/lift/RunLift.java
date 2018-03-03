package org.usfirst.frc.team2500.subSystems.lift;

import org.usfirst.frc.team2500.driverStation.Controller;
import org.usfirst.frc.team2500.robot.Robot;
import org.usfirst.frc.team2500.subSystems.climber.Climber;

import edu.wpi.first.wpilibj.command.Command;

public class RunLift extends Command {

	public RunLift(){
    	requires(Lift.getInstance());
	}
	
    protected void execute(){
    	Lift.getInstance().setSpeed(Controller.getInstance().get_Triggers());
    }
    
    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	end();
    }
}