package org.usfirst.frc.team2500.autonomusSubCommands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class UnloaderSubCommand extends CommandGroup {

	// true - Left
	// false - Right
	public UnloaderSubCommand(double degrees,double time, char robotSide){
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		if(gameData.charAt(1) == robotSide){
			addSequential(new DriveSubCommand(1, 1, 0));
	    	addSequential(new RotateSubCommand(degrees));
	    	addSequential(new UnloadSubCommand(time));
		}
		else if(gameData.charAt(1) == robotSide){
	    	addSequential(new RotateSubCommand(degrees));
	    	addSequential(new UnloadSubCommand(time));
		}
	}
}