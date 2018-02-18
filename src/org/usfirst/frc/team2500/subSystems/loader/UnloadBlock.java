package org.usfirst.frc.team2500.subSystems.loader;

import org.usfirst.frc.team2500.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class UnloadBlock extends Command {

    public UnloadBlock() {
        requires(Loader.getInstance());
        setTimeout(.9);
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	Loader.getInstance().setArm(true);
    	Loader.getInstance().setPower(1);
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