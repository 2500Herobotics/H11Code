package org.usfirst.frc.team2500.autonomus;

import org.usfirst.frc.team2500.autonomusSubCommands.AutoSubCommand;
import org.usfirst.frc.team2500.autonomusSubCommands.DriveSubCommand;
import org.usfirst.frc.team2500.autonomusSubCommands.UnloaderSubCommand;

import edu.wpi.first.wpilibj.command.Command;


public class AutoLeft extends Command {
	
	AutoSubCommand[] commands;
	int currentCommand;
	boolean finished;
	
	/**
     * This function is run once each time the robot enters autonomous mode
     */	
    public void initialize() {
		commands = new AutoSubCommand[4];
		commands[0] = new DriveSubCommand(1,1,0);
		commands[1] = new DriveSubCommand(1,1,45);
		commands[2] = new DriveSubCommand(1,1,0);
		commands[3] = new UnloaderSubCommand(90,1,true);
		currentCommand = 0;
		finished = false;
    }

    /**
     * This function is called periodically during autonomous
     */
    public void execute() {
		if(currentCommand == commands.length){
			finished = true;
		}
		if(commands[currentCommand].run()){
			currentCommand++;
		}
    }
    
    /**
     * This function is used to end the program
     * When it returns true the command finishes
     * Make sure it does this so it dosnt talk up cup power by running this command over and over again
     */
	@Override
	protected boolean isFinished() {
		return finished;
	}
}