package org.usfirst.frc.team2500.subSystems.loader;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleClaw extends Command {

    public ToggleClaw() {
        requires(Claw.getInstance());
    }

    protected void initialize() {
    	// Lower and open the claw if currently raised, otherwise close and raise
    	Claw claw = Claw.getInstance();
    	if (claw.getLowered()){
    		claw.setArm(false); //close claw
    		claw.setDeployed(false); //raise claw
    	}
    	else {
    		claw.setArm(true);
    		claw.setDeployed(true);
    	}
    }

    protected boolean isFinished() {
        return true;
    }
}