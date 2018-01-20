package org.usfirst.frc.team2500.robot;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem{

	private static final int WINCH_PORT = 6;
	
	private static Victor winch;
	
	public static void initialize(){
		winch = new Victor(WINCH_PORT);
	}
	
	public static void setPower(double speed){
		winch.set(speed);
	}

	@Override
	protected void initDefaultCommand() {
		
	}
}
