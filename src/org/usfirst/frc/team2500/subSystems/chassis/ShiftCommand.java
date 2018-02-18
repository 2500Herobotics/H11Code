package org.usfirst.frc.team2500.subSystems.chassis;

import edu.wpi.first.wpilibj.command.Command;

public class ShiftCommand extends Command {

	boolean finnished = false;

    protected void initialize() {
    	finnished = false;
    	
    	Chassis.getInstance().shift();
    	
    	finnished = true;
    }
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
