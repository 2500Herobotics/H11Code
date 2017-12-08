package org.usfirst.frc.team2500.teleops;

import org.usfirst.frc.team2500.robot.Main;

import edu.wpi.first.wpilibj.command.Command;


public class rotationTeleop extends Command {
	/**
     * This function is run once each time the robot enters autonomous mode
     */	
    public void initialize() {
    	
    }

    /**
     * This function is called periodically during autonomous
     */
    public void execute() {
    	double x = Main.driver1.getRawAxis(1);
    	double y = Main.driver1.getRawAxis(0);
    	double degree = Math.atan(y/x);
    	if(x < 0 && y > 0){
    		degree += (Math.PI/2);
    	}
    	else if(x < 0 && y < 0){
    		degree += Math.PI;
    	}
    	else if(x > 0 && y < 0){
    		degree += (Math.PI/2 * 3);
    	}
    	Main.robot.driveTrain.rotateTo(Math.toDegrees(degree));
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