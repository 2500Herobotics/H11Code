package org.usfirst.frc.team2500.autonomous.SubCommands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class Unloader extends CommandGroup {

	// true - Left
	// false - Right
	public UnloaderSubCommand(double degrees,int floor,double dist){

		addSequential(new Rotate(degrees));

		addSequential(new LiftBlock(floor));

		addSequential(new HoldBlock(floor,1));
		addParallel(new RetractArms());
		addParallel(new DriveDist(dist,dist,degrees));

		addSequential(new HoldBlock(floor,1));
		addParallel(new UnloadBlock());
		

		if(gameData.charAt(1) == robotSide){
			addSequential(new DriveSubCommand(1, 1, 0));
	    	addSequential(new UnloadSubCommand(time));
		}
		else if(gameData.charAt(1) == robotSide){
	    	addSequential(new RotateSubCommand(degrees));
	    	addSequential(new Unloader(time));
		}
	}
}