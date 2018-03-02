package org.usfirst.frc.team2500.subSystems.chassis;

import org.usfirst.frc.team2500.driverStation.Controller;

import edu.wpi.first.wpilibj.command.Command;

public class ArcadeDrive extends Command{

    public ArcadeDrive() {
    	//Make the code stop others from using the chassis when this is using it
        requires(Chassis.getInstance());
    }
    
    public void initialize(){
    	//Stop the chassis from trying to move to a point
        Chassis.getInstance().stopPID();
    }
    
    protected void execute(){
    	//Get the values that the motors should be moving at and set them
    	double turnValue = Controller.getTurn();
    	double moveValue = Controller.getMove();
    	Chassis.getInstance().arcadeDrive(turnValue, moveValue);
    }
    
    //Never quit teleop
    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	end();
    }
}
