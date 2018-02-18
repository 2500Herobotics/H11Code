package org.usfirst.frc.team2500.subSystems.lift;

import org.usfirst.frc.team2500.robot.Robot;
import org.usfirst.frc.team2500.subSystems.climber.Climber;

import edu.wpi.first.wpilibj.command.Command;

public class ResetEncoder extends Command {

    public ResetEncoder() {
    	
    }

    protected void initialize() {
    	Lift.getInstance().resetEncoder();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	end();
    }
}