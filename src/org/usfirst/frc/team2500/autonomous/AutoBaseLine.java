package org.usfirst.frc.team2500.autonomous;


import org.usfirst.frc.team2500.subSystems.chassis.Drive;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoBaseLine extends CommandGroup {

	public AutoBaseLine(){
    	addSequential(new Drive(AutoDistances.BASE_LINE_DIST,AutoDistances.BASE_LINE_DIST,-90));
	}
	
	public void initialize() {
		
    }
}