package org.usfirst.frc.team2500.autonomus;

import org.usfirst.frc.team2500.autonomusSubCommands.DriveSubCommand;
import org.usfirst.frc.team2500.autonomusSubCommands.UnloadSubCommand;
import org.usfirst.frc.team2500.autonomusSubCommands.UnloaderSubCommand;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;


public class AutoLeftSwitch extends CommandGroup {
	
	/**
     * This function is run once each time the robot enters autonomous mode
     */	
    public void initialize() {
    	addSequential(new DriveSubCommand(1,1,0));
    	addSequential(new DriveSubCommand(1,1,45));
    	addSequential(new DriveSubCommand(1,1,0));
    	addSequential(new UnloaderSubCommand(90,1,'L'));
    }
}