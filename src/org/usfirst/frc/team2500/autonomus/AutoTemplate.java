package org.usfirst.frc.team2500.autonomus;

import edu.wpi.first.wpilibj.command.Command;


public class AutoTemplate extends Command {
	int stage;
	/**
     * This function is run once each time the robot enters autonomous mode
     */	
    public void initialize() {
    	stage = 0;
    }

    /**
     * This function is called periodically during autonomous
     */
    public void execute() {
    	switch(stage){
    	case 0:
    		
    		break;
    	case 1:
    		
    		break;
    	}
    }
    
    /**
     * This function is used to end the program
     * When it returns true the command finishes
     * Make sure it does this so it dosnt talk up cup power by running this command over and over again
     */
	@Override
	protected boolean isFinished() {
		return true;
	}
}