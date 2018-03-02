package org.usfirst.frc.team2500.subSystems.chassis;

import org.usfirst.frc.team2500.driverStation.Controller;

import edu.wpi.first.wpilibj.command.Command;

public class ArcadeDrive extends Command{

	private final double speedScaler = 1;
	
    public ArcadeDrive() {
        requires(Chassis.getInstance());
        Chassis.getInstance().stopPID();
    }
    
    protected void execute(){
    	double turnValue = Controller.getTurn();
    	double moveValue = Controller.getMove();
    	Chassis.getInstance().arcadeDrive(turnValue, moveValue);
    	System.out.println(Chassis.getInstance().getRotation());
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
