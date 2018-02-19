package org.usfirst.frc.team2500.autonomous.SubCommands;

import org.usfirst.frc.team2500.subSystems.lift.Lift;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class LiftBlock extends Command {
	
	public LiftBlock(int floor){
		Lift.getInstance().setFloor(floor);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
}