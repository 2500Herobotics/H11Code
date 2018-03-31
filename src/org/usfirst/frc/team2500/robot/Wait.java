package org.usfirst.frc.team2500.robot;

import edu.wpi.first.wpilibj.command.Command;

public class Wait extends Command {

	public Wait(double time){
		setTimeout(time);
	}
	
	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}
}