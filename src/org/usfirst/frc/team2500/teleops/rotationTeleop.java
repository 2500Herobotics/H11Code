package org.usfirst.frc.team2500.teleops;

import org.usfirst.frc.team2500.driverStation.Controller;
import org.usfirst.frc.team2500.robot.Chassis;
import org.usfirst.frc.team2500.Main;

import edu.wpi.first.wpilibj.command.Command;


public class rotationTeleop extends Command {
	/**
     * This function is run once each time the robot enters teleop mode
     */	
    public void initialize() {
    	
    }

    /**
     * This function is called periodically during teleop
     */
    public void execute() {
    	double x = Controller.Pilot_Steering();
    	double y = Controller.Pilot_Throttle();
    	double degree = Math.atan(y/x);
    	if(x <= 0 && y > 0){
    		degree += (Math.PI/2);
    	}
    	else if(x < 0 && y <= 0){
    		degree += Math.PI;
    	}
    	else if(x >= 0 && y < 0){
    		degree += (Math.PI/2 * 3);
    	}
    	Chassis.rotateTo(Math.toDegrees(degree));
    }
    
    /**
     * This function is used to end the program
     * When it returns true the command finishes
     * Make sure it does this so it dosnt take up power by running this command over and over again
     */
	@Override
	protected boolean isFinished() {
		return true;
	}
}