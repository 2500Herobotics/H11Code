package org.usfirst.frc.team2500.subSystems.loader;

import edu.wpi.first.wpilibj.command.Command;

public class OpenClaw extends Command {

    public OpenClaw() {
        requires(Claw.getInstance());
    }

    protected void initialize() {
    	Claw.getInstance().setArm(true);
    }

    protected boolean isFinished() {
        return true;
    }
}