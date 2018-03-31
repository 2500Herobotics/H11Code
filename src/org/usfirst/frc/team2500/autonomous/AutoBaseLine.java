package org.usfirst.frc.team2500.autonomous;

import org.usfirst.frc.team2500.robot.Wait;
import org.usfirst.frc.team2500.subSystems.chassis.DriveDist;
import org.usfirst.frc.team2500.subSystems.chassis.HighGear;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoBaseLine extends CommandGroup {

	public AutoBaseLine(){
		//Drive to the baseline and then stop
		addSequential(new HighGear());
		addSequential(new Wait(0.4));
    	addSequential(new DriveDist(AutoDistances.BASE_LINE_DIST));
	}
	
	public void initialize() {
		System.out.println("Starting Baseline Auto");
    }
}