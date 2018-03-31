package org.usfirst.frc.team2500.subSystems.loader;

import edu.wpi.first.wpilibj.command.Command;

public class SetWheels extends Command {

	double speed;
	
    public SetWheels(double speed) {
        requires(Claw.getInstance());
        this.speed = speed;
    }

    protected void initialize() {
    	Claw.getInstance().setWheels(speed);;
    }

    protected boolean isFinished() {
        return true;
    }
}