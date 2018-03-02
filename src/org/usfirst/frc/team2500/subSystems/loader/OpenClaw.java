package org.usfirst.frc.team2500.subSystems.loader;

import org.usfirst.frc.team2500.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class OpenClaw extends Command {

    public OpenClaw() {
        requires(Claw.getInstance());
        setTimeout(.9);
    }

    protected void initialize() {
    	Claw.getInstance().setArm(true);
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