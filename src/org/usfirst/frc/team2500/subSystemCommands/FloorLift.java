package org.usfirst.frc.team2500.subSystemCommands;

import org.usfirst.frc.team2500.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class FloorLift extends Command{

    public FloorLift() {
        requires(Robot.lift);
        setTimeout(.9);
    }

    protected void initialize() {
    	Robot.lift.setTarget(0);
    	Robot.lift.start();
    }
    
    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
    	Robot.lift.stop();
    }

    protected void interrupted() {
    	end();
    }
}
