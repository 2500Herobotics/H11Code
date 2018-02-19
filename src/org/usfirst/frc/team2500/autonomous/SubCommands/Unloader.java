package org.usfirst.frc.team2500.autonomous.SubCommands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class Unloader extends CommandGroup {

	// true - Left
	// false - Right
	public Unloader(double degrees,int floor,double dist){

		addSequential(new Rotate(degrees));

		addSequential(new LiftBlock(floor));

		addSequential(new HoldBlock(floor,1));
		addParallel(new RetractArms());
		addParallel(new Drive(dist,dist,degrees));

		addSequential(new HoldBlock(floor,1));
		addParallel(new UnloadBlock());
	}
}