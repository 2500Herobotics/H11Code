package org.usfirst.frc.team2500.subSystems.chassis;

import org.usfirst.frc.team2500.driverStation.Controller;
import org.usfirst.frc.team2500.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveChassis extends Command{

	private double speedScaler;
	
    public DriveChassis(double speedScaler) {
        requires(Chassis.getInstance());
        this.speedScaler = speedScaler;
    }
    
    protected void execute(){
    	double turnValue = Controller.Pilot_Steering() * speedScaler;
    	System.out.println("turnValue: " + turnValue);
    	double moveValue = Controller.Pilot_Throttle() * speedScaler;
    	System.out.println("moveValue: " + moveValue);
    	
    	Chassis.getInstance().ChangeSpeed(turnValue, moveValue);
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
