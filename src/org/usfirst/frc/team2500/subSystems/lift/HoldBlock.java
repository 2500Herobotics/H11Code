package org.usfirst.frc.team2500.subSystems.lift;

import edu.wpi.first.wpilibj.command.Command;

public class HoldBlock extends Command {
	
	int floor;
	
	public HoldBlock(int floor, double time){
		setTimeout(time);
		Lift.getInstance().setFloor(floor);
	}
	
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return isTimedOut();
	}
}