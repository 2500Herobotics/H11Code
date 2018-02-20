package org.usfirst.frc.team2500.subSystems.chassis;

import edu.wpi.first.wpilibj.command.Command;

public class ShiftCommand extends Command {

	boolean finnished = false;

    protected void initialize() {
    	finnished = false;
    	
    	Chassis.getInstance().shiftTarget = !Chassis.getInstance().shiftTarget;
    	
    	finnished = true;
    }
	
	@Override
	protected boolean isFinished() {
		return finnished;
	}

}
