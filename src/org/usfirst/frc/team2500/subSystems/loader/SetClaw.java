package org.usfirst.frc.team2500.subSystems.loader;

import edu.wpi.first.wpilibj.command.Command;

public class SetClaw extends Command {

	boolean state;
	
    public SetClaw(boolean state) {
        requires(Claw.getInstance());
        this.state = state;
    }

    protected void initialize() {
    	Claw.getInstance().setDeployed(state);
    }

    protected boolean isFinished() {
        return true;
    }
}