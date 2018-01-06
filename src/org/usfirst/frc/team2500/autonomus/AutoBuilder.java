package org.usfirst.frc.team2500.autonomus;

import org.usfirst.frc.team2500.robot.Chassis;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;


public class AutoBuilder extends Command {
	double leftDistance;
	double rightDistance;
	/**
     * This function is run once each time the robot enters autonomous mode
     */	
    public void initialize() {
    	leftDistance = NetworkTable.getTable("SmartDashboard").getNumber("leftDistance",0);
    	rightDistance = NetworkTable.getTable("SmartDashboard").getNumber("rightDistance",0);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void execute() {
    	//Chassis.MoveTo(leftDistance,rightDistance);
    	
    	if(NetworkTable.getTable("SmartDashboard").getBoolean("AutoBuilderRun", false)){
    		Chassis.resetEncoders();
        	leftDistance = NetworkTable.getTable("SmartDashboard").getNumber("leftDistance",0);
        	rightDistance = NetworkTable.getTable("SmartDashboard").getNumber("rightDistance",0);
    	}
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