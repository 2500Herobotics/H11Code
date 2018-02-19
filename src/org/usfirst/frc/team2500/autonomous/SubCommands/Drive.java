package org.usfirst.frc.team2500.autonomous.SubCommands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Drive extends CommandGroup {
	
	public Drive(double leftDistence, double rightDistence,double degrees){
    	addSequential(new RotateSubCommand(degrees));
    	addSequential(new DriveDistSubCommand(leftDistence, rightDistence));
	}
}