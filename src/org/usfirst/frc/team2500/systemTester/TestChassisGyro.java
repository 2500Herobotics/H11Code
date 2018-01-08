package org.usfirst.frc.team2500.systemTester;

import org.usfirst.frc.team2500.robot.Chassis;

import edu.wpi.first.wpilibj.command.Command;

public class TestChassisGyro extends Command {

	public static double RotationTarget = 180;
	
	boolean finished = false;
	
	public void initialize() {
		System.out.println("Testing Chassis Gyro");
		Chassis.resetGyro();
		finished = false;
	}

	/**
	* This function is called periodically during autonomous
	*/
	public void execute() {
		double error = RotationTarget - Chassis.getAngle();
		Chassis.ChangePower(error, error * -1);
		if(error < 0){
			System.out.println("Gyro tested. Rotated 180 degrees");
			finished = true;
		}
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
