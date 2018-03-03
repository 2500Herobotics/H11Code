package org.usfirst.frc.team2500.subSystems.loader;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleClaw extends Command {

    public ToggleClaw() {
        requires(Claw.getInstance());
    }

    protected void initialize() {
    	Claw.getInstance().toggleArm();
    	System.out.println("Jaw Toggle");
    }

    protected boolean isFinished() {
        return true;
    }
}