package org.usfirst.frc.team2500.autonomous;


import org.usfirst.frc.team2500.subSystems.chassis.Drive;
import org.usfirst.frc.team2500.subSystems.chassis.LowGear;
import org.usfirst.frc.team2500.subSystems.lift.LiftTime;
import org.usfirst.frc.team2500.subSystems.loader.OpenClaw;
import org.usfirst.frc.team2500.subSystems.loader.ToggleClaw;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCenter extends CommandGroup {
	
	public AutoCenter(){
		addSequential(new LowGear());
		
    	//Get off of the wall so we can turn
    	addSequential(new Drive(AutoDistances.OFF_WALL_DIST,0));

		addParallel(new LiftTime(4));
		//Test what side the Switch is on and go to that side
		if(DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'L'){
			addSequential(new Drive(AutoDistances.SWITCH_ALIGN_DIST_LEFT,AutoDistances.SWITCH_ALIGN_DEG_LEFT));
			addSequential(new Drive(AutoDistances.TO_SWITCH_DIST,-AutoDistances.SWITCH_ALIGN_DEG_LEFT));
    	}
		else{
			addSequential(new Drive(AutoDistances.SWITCH_ALIGN_DIST_RIGHT,AutoDistances.SWITCH_ALIGN_DEG_RIGHT));
			addSequential(new Drive(AutoDistances.TO_SWITCH_DIST,-AutoDistances.SWITCH_ALIGN_DEG_RIGHT));
    	}
		System.out.println("Running the command");
		//Unload the block
		addSequential(new ToggleClaw());
	}
	
    public void initialize() {
    	System.out.println("Starting center auto " + (DriverStation.getInstance().getGameSpecificMessage().charAt(0)));
    }
}