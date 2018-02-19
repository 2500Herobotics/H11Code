package org.usfirst.frc.team2500.subSystems.lift;

import org.usfirst.frc.team2500.robot.Robot;
import org.usfirst.frc.team2500.subSystems.climber.Climber;

import edu.wpi.first.wpilibj.command.Command;

public class DecreaseFloor extends Command {

    public DecreaseFloor() {
    	
    }

    protected void initialize() {
    	Lift.getInstance().decreaseFloor();
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