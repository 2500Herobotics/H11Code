package org.usfirst.frc.team2500.autonomous;

import org.usfirst.frc.team2500.autonomous.SubCommands.Drive;
import org.usfirst.frc.team2500.autonomous.SubCommands.Unloader;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoRight extends CommandGroup {
	
	/**
     * This function is run once each time the robot enters autonomous mode
     */	
    public void initialize() {
    	// Move away from the wall to be able to turn
    	addSequential(new Drive(AutoDistances.OFF_WALL_DIST,AutoDistances.OFF_WALL_DIST,0));
    	//Turn and move to the side of the switch
    	addSequential(new Drive(AutoDistances.SCALE_FIRST_ALIGN_DIST,AutoDistances.SCALE_FIRST_ALIGN_DIST,AutoDistances.SCALE_FIRST_ALIGN_DEG_LEFT));
    	//Move past the switch
    	addSequential(new Drive(AutoDistances.SCALE_INTERSECTION_DIST,AutoDistances.SCALE_INTERSECTION_DIST,0));
    	
    	//Find out what side to go to
    	String gameData = DriverStation.getInstance().getGameSpecificMessage();
    	double degree = 270;
    	if(gameData.charAt(1) == 'r'){
    		//If target is on the left side
    		addSequential(new Drive(AutoDistances.SCALE_CROSS_DIST,AutoDistances.SCALE_CROSS_DIST,270));
    		degree = 90;
    	}
		addSequential(new Drive(AutoDistances.SCALE_ALIGN_DIST,AutoDistances.SCALE_ALIGN_DIST,0));
    	addSequential(new Unloader(degree, 4, AutoDistances.SCALE_DIST));
    }
    
}