package org.usfirst.frc.team2500.autonomusSubCommands;

public class DriveSubCommand implements AutoSubCommand {
	
	AutoSubCommand[] commands;
	int currentCommand;
	
	
	public DriveSubCommand(double leftDistence, double rightDistence,double degrees){
		commands = new AutoSubCommand[2];
		commands[0] = new RotateSubCommand(degrees);
		commands[1] = new DriveDistSubCommand(leftDistence, rightDistence);
		currentCommand = 0;
	}
	
	public boolean run(){
		if(currentCommand == commands.length){
			return true;
		}
		if(commands[currentCommand].run()){
			currentCommand++;
		}
		return false;
	}
}