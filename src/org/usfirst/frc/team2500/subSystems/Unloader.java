package org.usfirst.frc.team2500.subSystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Unloader extends Subsystem {
	private final int MOTOR_PORT = 5;
	
	private Victor motor;
	
	public Unloader(){
		motor = new Victor(MOTOR_PORT);
	}
	
	public void setPower(double speed){
		motor.set(speed);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
}
