package org.usfirst.frc.team2500.autonomusSubCommands;

public class UnloaderSubCommand implements AutoSubCommand {
	
	AutoSubCommand[] commands;
	int currentCommand;
	boolean side;
	
	// true - Left
	// false - Right
	public UnloaderSubCommand(double degrees,double time, boolean side){
		commands = new AutoSubCommand[2];
		commands[0] = new RotateSubCommand(degrees);
		commands[1] = new UnloadSubCommand(time);
		currentCommand = 0;
		this.side = side;
	}
	
	public boolean run(){
		if(side){
			if(currentCommand == commands.length){
				return true;
			}
			if(commands[currentCommand].run()){
				currentCommand++;
			}
		}
		else{
			//Just drive to bace line if we are on the wrong side of the switch
			if(commands[0].run()){
				return true;
			}
		}
		return false;
	}
}