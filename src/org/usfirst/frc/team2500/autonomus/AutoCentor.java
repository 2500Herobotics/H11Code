package org.usfirst.frc.team2500.autonomus;

import org.usfirst.frc.team2500.autonomusSubCommands.DriveDistSubCommand;
import org.usfirst.frc.team2500.autonomusSubCommands.DriveSubCommand;
import org.usfirst.frc.team2500.autonomusSubCommands.RotateSubCommand;
import org.usfirst.frc.team2500.autonomusSubCommands.UnloadSubCommand;
import org.usfirst.frc.team2500.autonomusSubCommands.UnloaderSubCommand;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCentor extends CommandGroup {
	
    public void initialize() {
    	addSequential(new DriveSubCommand(1,1,0));
		//Test what side the Switch is on and go to that side
		if(DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'L'){
			addSequential(new DriveSubCommand(1,1,45));
		}
		else{
			addSequential(new DriveSubCommand(1,1,-45));
		}
		addSequential(new DriveSubCommand(1,1,0));
		addSequential(new UnloadSubCommand(1));
    }
}