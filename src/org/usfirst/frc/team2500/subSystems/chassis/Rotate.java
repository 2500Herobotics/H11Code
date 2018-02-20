package org.usfirst.frc.team2500.subSystems.chassis;

import org.usfirst.frc.team2500.driverStation.Controller;
import org.usfirst.frc.team2500.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveChassis extends PIDCommand{

	private final double speedScaler = 1;
	
    public DriveChassis() {
        requires(Chassis.getInstance());
        Chassis.getInstance().stopPID();
    }
    
    protected void execute(){
        //TODO
    	double turnValue = 1;
    	System.out.println("turnValue: " + turnValue);
    	
    	Chassis.getInstance().arcadeDrive(turnValue, 0);
    }
    
    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	end();
    }
}
