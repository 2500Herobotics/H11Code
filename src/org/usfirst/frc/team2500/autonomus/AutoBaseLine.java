package org.usfirst.frc.team2500.autonomus;

import org.usfirst.frc.team2500.autonomusSubCommands.AutoSubCommand;
import org.usfirst.frc.team2500.autonomusSubCommands.DriveSubCommand;
import org.usfirst.frc.team2500.autonomusSubCommands.UnloadSubCommand;
import org.usfirst.frc.team2500.autonomusSubCommands.UnloaderSubCommand;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

public class AutoBaseLine extends Command {

	
	AutoSubCommand command;
	int currentCommand;
	boolean finished;
	
	/**
     * This function is run once each time the robot enters autonomous mode
     */	
    public void initialize() {
		command = new DriveSubCommand(1,1,0);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void execute() {
		finished = command.run();
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