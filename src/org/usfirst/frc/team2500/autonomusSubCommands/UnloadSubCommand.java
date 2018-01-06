package org.usfirst.frc.team2500.autonomusSubCommands;

import org.usfirst.frc.team2500.robot.Unloader;

public class UnloadSubCommand implements AutoSubCommand {
	
	double timeLeft;
	double currentTime;
	
	public UnloadSubCommand(double time){
		timeLeft = time;
		currentTime = System.currentTimeMillis();
	}
	
	public boolean run(){
		double deltaTime = System.currentTimeMillis() - currentTime;
		currentTime += deltaTime;
		timeLeft -= deltaTime;
		
		Unloader.setPower(1);
		
		return timeLeft < 0;
	}
}