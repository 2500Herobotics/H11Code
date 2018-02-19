package org.usfirst.frc.team2500.autonomous;


import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCentor extends CommandGroup {
	
    public void initialize() {
    	addSequential(new DriveSubCommand(AutoDistances.OFF_WALL_DIST,AutoDistances.OFF_WALL_DIST,0));
		//Test what side the Switch is on and go to that side
		if(DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'L'){
			addSequential(new DriveSubCommand(AutoDistances.SWITCH_ALIGN_DIST,AutoDistances.SWITCH_ALIGN_DIST,AutoDistances.SWITCH_ALIGN_DEG_LEFT));
    	}
		else{
			addSequential(new DriveSubCommand(AutoDistances.SWITCH_ALIGN_DIST,AutoDistances.SWITCH_ALIGN_DIST,AutoDistances.SWITCH_ALIGN_DEG_RIGHT));
    	}
		addSequential(new DriveSubCommand(AutoDistances.SWITCH_DIST,AutoDistances.SWITCH_DIST,0));
		addSequential(new Unloader(1));
    }
}