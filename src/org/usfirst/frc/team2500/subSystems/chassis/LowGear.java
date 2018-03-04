package org.usfirst.frc.team2500.subSystems.chassis;

import edu.wpi.first.wpilibj.command.Command;

public class LowGear extends Command {

	public LowGear(){
		System.out.println("Shifting low gears");
	}
	
    protected void initialize() {
    	Chassis.getInstance().shiftTarget = false;
    }
	
	@Override
	protected boolean isFinished() {
		return true;
	}
}