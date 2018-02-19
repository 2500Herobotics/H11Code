package org.usfirst.frc.team2500.autonomous;

import org.usfirst.frc.team2500.autonomous.SubCommands.DriveSubCommand;
import org.usfirst.frc.team2500.autonomous.SubCommands.UnloaderSubCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;


public class AutoRight extends CommandGroup {
	
	/**
     * This function is run once each time the robot enters autonomous mode
     */	
    public void initialize() {
    	addSequential(new DriveSubCommand(AutoDistances.OFF_WALL_DIST,AutoDistances.OFF_WALL_DIST,0));
    	addSequential(new DriveSubCommand(AutoDistances.FIRST_ALIGN_DIST,AutoDistances.FIRST_ALIGN_DIST,AutoDistances.FIRST_ALIGN_DEG_RIGHT));
    	addSequential(new DriveSubCommand(1,1,0));
    	addSequential(new UnloaderSubCommand(270,1,'L'));
    }
    
}