package org.usfirst.frc.team2500.autonomous;

import org.usfirst.frc.team2500.subSystems.chassis.Drive;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoRight extends CommandGroup {
	
	public AutoRight(){
    	// Move away from the wall to be able to turn
    	addSequential(new Drive(AutoDistances.OFF_WALL_DIST,0));
    	//Move past the switch
    	addSequential(new Drive(AutoDistances.SCALE_INTERSECTION_DIST,0));
    	
    	//Find out what side to go to
    	String gameData = DriverStation.getInstance().getGameSpecificMessage();
    	double degree = 270;
    	if(gameData.charAt(1) == 'r'){
    		//If target is on the left side
    		addSequential(new Drive(AutoDistances.SCALE_CROSS_DIST,270));
    		degree = 90;
    	}
		addSequential(new Drive(AutoDistances.SCALE_ALIGN_DIST,0));
		
	}
	/**
     * This function is run once each time the robot enters autonomous mode
     */	
    public void initialize() {
    	System.out.println("Starting Right Auto");
    }
}