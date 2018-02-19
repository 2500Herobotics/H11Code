package org.usfirst.frc.team2500.autonomous.SubCommands;

import org.usfirst.frc.team2500.robot.Robot;
import org.usfirst.frc.team2500.subSystems.chassis.Chassis;
import org.usfirst.frc.team2500.subSystems.loader.Loader;

import edu.wpi.first.wpilibj.command.Command;

public class Unload extends Command {
	
	public UnloadSubCommand(){
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