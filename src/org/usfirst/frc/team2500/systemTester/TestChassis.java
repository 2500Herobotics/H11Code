package org.usfirst.frc.team2500.systemTester;

import edu.wpi.first.wpilibj.command.Command;

public class TestChassis extends Command {
	
	Command commands[];
	int currentCommand;
	boolean finished = false;
	
	public void initialize() {
		System.out.println("Testing Chassis sub systems");
		finished = false;
		commands = new Command[2];
		commands[0] = new TestChassisEncoders();
		commands[1] = new TestChassisGyro();
		
		currentCommand = 0;
		
		commands[currentCommand].start();
	}

	/**
	* This function is called periodically during autonomous
	*/
	public void execute() {
		if(!commands[currentCommand].isRunning()){
			if(currentCommand == commands.length){
				System.out.println("All Chassis sub systems tested.");
				finished = true;
			}
			else{
				currentCommand++;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				commands[currentCommand].start();
			}
		}
	}
	
	@Override
	protected boolean isFinished() {
		return finished;
	}
}