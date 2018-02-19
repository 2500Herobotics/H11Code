package org.usfirst.frc.team2500.autonomous;


import org.usfirst.frc.team2500.autonomous.SubCommands.Drive;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoBaseLine extends CommandGroup {

	public void initialize() {
    	addSequential(new Drive(AutoDistances.BASE_LINE_DIST,AutoDistances.BASE_LINE_DIST,0));
    }
    
    /**
     * This function is used to end the program
     * When it returns true the command finishes
     * Make sure it does this so it dosnt talk up cup power by running this command over and over again
     */
	@Override
	protected boolean isFinished() {
		return false;
	}
}