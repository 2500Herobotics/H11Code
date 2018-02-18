package org.usfirst.frc.team2500.autonomous.SubCommands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveSubCommand extends CommandGroup {
	
	public DriveSubCommand(double leftDistence, double rightDistence,double degrees){
    	addSequential(new RotateSubCommand(degrees));
    	addSequential(new DriveDistSubCommand(leftDistence, rightDistence));
	}
}