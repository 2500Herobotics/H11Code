package org.usfirst.frc.team2500.robot;

import edu.wpi.first.wpilibj.Victor;

public class Unloader {
	private static final int MOTOR_PORT = 5;
	
	private static Victor motor;
	
	public static void initialize(){
		motor = new Victor(MOTOR_PORT);
	}
	
	public static void setPower(double speed){
		motor.set(speed);
	}
}
