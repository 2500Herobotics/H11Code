package org.usfirst.frc.team2500.subSystemCommands;

import org.usfirst.frc.team2500.driverStation.Controller;
import org.usfirst.frc.team2500.robot.Robot;
import org.usfirst.frc.team2500.subSystems.Chassis;

import edu.wpi.first.wpilibj.command.Command;

public class DataPrinter extends Command {
	
	public DataPrinter() {
        requires(Chassis.getInstance());
        setTimeout(.9);
    }

    protected void initialize() {
    	
    }
    
    protected void execute(){
    	System.out.print(Controller.Pilot_Steering() + "\t");
    	System.out.print(Controller.Pilot_Throttle() + "\t");
    	System.out.print(Chassis.getInstance().getLeftRate() + "\t");
    	System.out.print(Chassis.getInstance().getRightRate() + "\t");
    }
    
    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	end();
    }
}
