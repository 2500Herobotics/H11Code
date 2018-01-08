package org.usfirst.frc.team2500.teleops;

import org.usfirst.frc.team2500.driverStation.Controller;
import org.usfirst.frc.team2500.robot.Chassis;

import edu.wpi.first.wpilibj.command.Command;


public class outreachTeleop extends Command {
	/**
     * This function is run once each time the robot enters teleop mode
     */	
    public void initialize() {
    	
    }

    /**
     * This function is called periodically during teleop
     */
    public void execute() {
    	double turnValue = Controller.Pilot_Steering() * 0.5;
    	double moveValue = Controller.Pilot_Throttle() * 0.5;
    	Chassis.ChangePower(turnValue, moveValue);
    }
    
    /**
     * This function is used to end the program
     * When it returns true the command finishes
     * Make sure it does this so it dosnt take up power by running this command over and over again
     */
	@Override
	protected boolean isFinished() {
		return false;
	}
}