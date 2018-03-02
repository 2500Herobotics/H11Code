package org.usfirst.frc.team2500.subSystems.lift;

import org.usfirst.frc.team2500.subSystems.chassis.Drive;
import org.usfirst.frc.team2500.subSystems.chassis.Rotate;
import org.usfirst.frc.team2500.subSystems.loader.ToggleClaw;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Unloader extends CommandGroup {

	// true - Left
	// false - Right
	public Unloader(double degrees,int floor,double dist){

		addSequential(new Rotate(degrees));

		addSequential(new LiftBlock(floor));

		addSequential(new HoldBlock(floor,1));
		addParallel(new Drive(dist,dist,degrees));

		addSequential(new HoldBlock(floor,1));
		addParallel(new ToggleClaw());
	}
}