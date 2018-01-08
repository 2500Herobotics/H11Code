package org.usfirst.frc.team2500.systemTester;

import org.usfirst.frc.team2500.robot.Chassis;

import edu.wpi.first.wpilibj.command.Command;

public class TestChassisShifter extends Command {
	boolean finished = false;
	
	public void initialize() {
		System.out.println("Testing Chassis Shifters");
		Chassis.resetGyro();
		finished = false;
	}

	/**
	* This function is called periodically during autonomous
	*/
	public void execute() {
		Chassis.shift(true);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Chassis.shift(false);
		System.out.print("Shifted to high gear for 1 second then back to low gear.");
		finished = true;
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
