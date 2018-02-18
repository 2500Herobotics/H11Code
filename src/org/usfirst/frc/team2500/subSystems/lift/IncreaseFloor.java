package org.usfirst.frc.team2500.subSystems.lift;

import org.usfirst.frc.team2500.robot.Robot;
import org.usfirst.frc.team2500.subSystems.climber.Climber;

import edu.wpi.first.wpilibj.command.Command;

public class IncreaseFloor extends Command {

    public IncreaseFloor() {
    	
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	Lift.getInstance().increaseFloor();
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