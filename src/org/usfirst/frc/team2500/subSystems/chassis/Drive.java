package org.usfirst.frc.team2500.subSystems.chassis;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Drive extends CommandGroup {
	
	public Drive(double distence,double degrees){
		//Turn to a degree
    	addSequential(new Rotate(degrees));
    	//Move a distance
    	addSequential(new DriveDist(distence));
	}
}