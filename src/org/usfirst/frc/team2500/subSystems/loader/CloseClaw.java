package org.usfirst.frc.team2500.subSystems.loader;

import org.usfirst.frc.team2500.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CloseClaw extends Command {

    public CloseClaw() {
        requires(Claw.getInstance());
        setTimeout(.9);
    }

    protected void initialize() {
    	Claw.getInstance().setArm(false);
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