package org.usfirst.frc.team2500.subSystemCommands;

import org.usfirst.frc.team2500.driverStation.Controller;
import org.usfirst.frc.team2500.robot.Chassis;
import org.usfirst.frc.team2500.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveChassis extends Command{

	private double speedScaler;
	
    public DriveChassis(double speedScaler) {
        requires(Robot.chassis);
        setTimeout(.9);
        this.speedScaler = speedScaler;
    }

    protected void initialize() {
    	
    }
    
    protected void execute(){
    	double turnValue = Controller.Pilot_Steering() * speedScaler;
    	System.out.println("turnValue: " + turnValue);
    	double moveValue = Controller.Pilot_Throttle() * speedScaler;
    	System.out.println("moveValue: " + moveValue);
    	
    	Chassis.ChangePower(turnValue, moveValue);
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
