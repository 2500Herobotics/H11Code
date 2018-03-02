package org.usfirst.frc.team2500.subSystems.loader;

import edu.wpi.first.wpilibj.command.Command;

public class CloseClaw extends Command {

    public CloseClaw() {
        requires(Claw.getInstance());
    }

    protected void initialize() {
    	Claw.getInstance().setArm(false);
    }

    protected boolean isFinished() {
        return true;
    }
}