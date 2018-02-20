package org.usfirst.frc.team2500.subSystems.lift;

import org.usfirst.frc.team2500.robot.Robot;
import org.usfirst.frc.team2500.subSystems.chassis.Chassis;
import org.usfirst.frc.team2500.subSystems.loader.Loader;

import edu.wpi.first.wpilibj.command.Command;

public class UnloadBlock extends Command {
	
	public UnloadBlock(){
		requires(Lift.getInstance());
        setTimeout(.9);
	}
	
	public void execute(){
        //Fire piston
	}

	@Override
    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	end();
    }
}