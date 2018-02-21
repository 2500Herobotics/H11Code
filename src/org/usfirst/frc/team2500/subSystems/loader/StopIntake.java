package org.usfirst.frc.team2500.subSystems.loader;

import org.usfirst.frc.team2500.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class StopIntake extends Command {

    public StopIntake() {
        requires(Loader.getInstance());
        setTimeout(.9);
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	Loader.getInstance().setArm(true);
    	Loader.getInstance().setPower(0);
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