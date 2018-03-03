package org.usfirst.frc.team2500.subSystems.chassis;

import edu.wpi.first.wpilibj.command.Command;

public class ShiftCommand extends Command {

	public ShiftCommand(){
		System.out.println("Shifting drive gears");
	}
	
    protected void initialize() {
    	Chassis.getInstance().shiftTarget = !Chassis.getInstance().shiftTarget;
    }
	
	@Override
	protected boolean isFinished() {
		return true;
	}
}