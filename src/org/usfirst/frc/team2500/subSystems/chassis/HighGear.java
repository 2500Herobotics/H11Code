package org.usfirst.frc.team2500.subSystems.chassis;

import edu.wpi.first.wpilibj.command.Command;

public class HighGear extends Command {

	public HighGear(){
		System.out.println("Shifting high gear");
	}
	
    protected void initialize() {
    	Chassis.getInstance().shiftTarget = true;
    }
	
	@Override
	protected boolean isFinished() {
		return true;
	}
}