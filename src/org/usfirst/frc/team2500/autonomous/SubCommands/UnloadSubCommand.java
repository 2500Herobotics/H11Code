package org.usfirst.frc.team2500.autonomous.SubCommands;

import org.usfirst.frc.team2500.robot.Robot;
import org.usfirst.frc.team2500.subSystems.chassis.Chassis;
import org.usfirst.frc.team2500.subSystems.loader.Loader;

import edu.wpi.first.wpilibj.command.Command;

public class UnloadSubCommand extends Command {
	
	double timeLeft;
	double currentTime;
	
	boolean finished = false;
	
	public UnloadSubCommand(double time){
		requires(Chassis.getInstance());
		timeLeft = time;
		currentTime = System.currentTimeMillis();
		finished = false;
	}
	
	public void execute(){
		double deltaTime = System.currentTimeMillis() - currentTime;
		currentTime += deltaTime;
		timeLeft -= deltaTime;
		
//		Robot.unloader.setPower(1);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return timeLeft < 0;
	}
}