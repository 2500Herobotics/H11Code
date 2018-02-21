package org.usfirst.frc.team2500.subSystems.lift;

import org.usfirst.frc.team2500.driverStation.Controller;
import org.usfirst.frc.team2500.robot.Robot;
import org.usfirst.frc.team2500.subSystems.climber.Climber;

import edu.wpi.first.wpilibj.command.Command;

public class MoveLift extends Command {

    protected void initialize() {
//    	requires(Lift.getInstance());
    	Lift.getInstance().stop();
    }
    
    protected void execute(){
//    	Lift.getInstance().setSpeed(Controller.Get_Triggers());
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