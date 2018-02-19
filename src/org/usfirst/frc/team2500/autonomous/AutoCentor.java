package org.usfirst.frc.team2500.autonomous;


import org.usfirst.frc.team2500.autonomous.SubCommands.Drive;
import org.usfirst.frc.team2500.autonomous.SubCommands.Unloader;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCentor extends CommandGroup {
	
    public void initialize() {
    	//Get off of the wall so we can turn
    	addSequential(new Drive(AutoDistances.OFF_WALL_DIST,AutoDistances.OFF_WALL_DIST,0));
		//Test what side the Switch is on and go to that side
		if(DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'L'){
			addSequential(new Drive(AutoDistances.SWITCH_ALIGN_DIST,AutoDistances.SWITCH_ALIGN_DIST,AutoDistances.SWITCH_ALIGN_DEG_LEFT));
    	}
		else{
			addSequential(new Drive(AutoDistances.SWITCH_ALIGN_DIST,AutoDistances.SWITCH_ALIGN_DIST,AutoDistances.SWITCH_ALIGN_DEG_RIGHT));
    	}
		//Move to the wall
		addSequential(new Drive(AutoDistances.SWITCH_DIST,AutoDistances.SWITCH_DIST,0));
		//Unload the block
		addSequential(new Unloader(0, 1, 1));
    }
}