package org.usfirst.frc.team2500.autonomusSubCommands;

import edu.wpi.first.wpilibj.DriverStation;

public class UnloaderSubCommand implements AutoSubCommand {
	
	AutoSubCommand[] commands;
	int currentCommand;
	char side;
	String gameData;
	
	// true - Left
	// false - Right
	public UnloaderSubCommand(double degrees,double time, char side){
		commands = new AutoSubCommand[2];
		commands[0] = new RotateSubCommand(degrees);
		commands[1] = new UnloadSubCommand(time);
		currentCommand = 0;
		this.side = side;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
	}
	
	public boolean run(){
		if(side != gameData.charAt(0) || currentCommand == commands.length){
			return true;
		}
		if(commands[currentCommand].run()){
			currentCommand++;
		}
		return false;
	}
}