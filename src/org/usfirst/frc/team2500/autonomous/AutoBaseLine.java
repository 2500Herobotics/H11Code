package org.usfirst.frc.team2500.autonomous;

import org.usfirst.frc.team2500.subSystems.chassis.DriveDist;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoBaseLine extends CommandGroup {

	public AutoBaseLine(){
		//Drive to the baseline and then stop
    	addSequential(new DriveDist(AutoDistances.BASE_LINE_DIST,AutoDistances.BASE_LINE_DIST));
	}
	
	public void initialize() {
		
    }
}