package org.usfirst.frc.team2500.teleops;

import org.usfirst.frc.team2500.subSystemCommands.DriveChassis;

import edu.wpi.first.wpilibj.command.Command;

public class competitionTeleop extends Command {
	
	Command[] commands;
	
	/**
     * This function is run once each time the robot enters teleop mode
     */	
    public void initialize() {
    	commands = new Command[1];
    	commands[0] = new DriveChassis(1);
    	
    	for(Command command : commands){
    		command.start();
    	}
    }
    
    /**
     * This function is called periodically during teleop
     */
    public void execute() {
    	
    }
    
    /**
     * This function is used to end the program
     * When it returns true the command finishes
     * Make sure it does this so it dosnt take up power by running this command over and over again
     */
	@Override
	protected boolean isFinished() {
		return false;
	}
	
    protected void end() {
    	for(Command command : commands){
    		command.cancel();
    	}
    }
    
    //Run when command is canceled
    protected void interrupted() {
    	end();
    }
}