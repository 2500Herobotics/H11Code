package org.usfirst.frc.team2500.systemTester;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SystemTesterStartUp extends Command {
	
	boolean finished = false;
	
	public void initialize() {
		finished = false;
		SmartDashboard.putData("Auto mode", new TestAllSystems());
		SmartDashboard.putData("Auto mode", new TestChassis());
		finished = true;
	}
	
	public void execute() {
		
	}
	    
	/**
	* This function is used to end the program
	* When it returns true the command finishes
	* Make sure it does this so it dosnt talk up cup power by running this command over and over again
	*/
	@Override
	protected boolean isFinished() {
		return true;
	}
}
