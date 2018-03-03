package org.usfirst.frc.team2500.driverStation;

import org.usfirst.frc.team2500.subSystems.chassis.Chassis;
import org.usfirst.frc.team2500.subSystems.loader.Claw;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DashboardUpdater extends Command{
    
	protected void execute(){
    	SmartDashboard.putNumber("Lift Speed", Controller.getInstance().get_Triggers());
    	SmartDashboard.putNumber("Pilot Throttle", Controller.getInstance().getMove());
    	SmartDashboard.putNumber("Pilot Steering",Controller.getInstance().getTurn());
    	SmartDashboard.putNumber("Left Speed", Chassis.getInstance().getLeftRate());
    	SmartDashboard.putNumber("Right Speed", Chassis.getInstance().getRightRate());
    	SmartDashboard.putNumber("Left Dist", Chassis.getInstance().getLeftDistance());
    	SmartDashboard.putNumber("Right Dist", Chassis.getInstance().getRightDistance());
    	SmartDashboard.putNumber("Rotation", Chassis.getInstance().getRotation());
    	SmartDashboard.putBoolean("Claw", Claw.getInstance().getState());
    	SmartDashboard.putBoolean("Gear", Chassis.getInstance().getGear());
    	SmartDashboard.putBoolean("Gear Target", Chassis.getInstance().getGearTarget());
    }
    
    protected boolean isFinished() {
        return false;
    }
}
