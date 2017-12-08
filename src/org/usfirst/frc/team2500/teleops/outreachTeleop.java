package org.usfirst.frc.team2500.teleops;

import edu.wpi.first.wpilibj.command.Command;


public class outreachTeleop extends Command {
	/**
     * This function is run once each time the robot enters autonomous mode
     */	
    public void initialize() {
    	
    }

    /**
     * This function is called periodically during autonomous
     */
    public void execute() {
    	
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