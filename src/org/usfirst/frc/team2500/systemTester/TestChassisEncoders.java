package org.usfirst.frc.team2500.systemTester;

import org.usfirst.frc.team2500.robot.Chassis;

import edu.wpi.first.wpilibj.command.Command;

public class TestChassisEncoders extends Command {

	public static double LeftSideTarget = 100;
	public static double RightSideTarget = 100;
	
	boolean finished = false;
	
	public void initialize() {
		System.out.println("Testing Chassis encoders");
		Chassis.resetEncoders();
		finished = false;
	}

	/**
	* This function is called periodically during autonomous
	*/
	public void execute() {
		double leftError = LeftSideTarget - Chassis.getLeftDist();
		double rightError= RightSideTarget - Chassis.getRightDist();
		Chassis.ChangePower(leftError, rightError);
		if(leftError < 0 && rightError < 0){
			System.out.println("Left and Right encoders moved to 100");
			finished = true;
		}
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
