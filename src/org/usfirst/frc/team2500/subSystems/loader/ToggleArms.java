package org.usfirst.frc.team2500.subSystems.loader;

import org.usfirst.frc.team2500.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleArms extends Command {

    public ToggleArms() {
        requires(Loader.getInstance());
        setTimeout(.9);
    }

    protected void initialize() {
    	Loader.getInstance().toggleArm();
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