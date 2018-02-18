package org.usfirst.frc.team2500.subSystems.climber;

import org.usfirst.frc.team2500.robot.RobotPorts;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem{
	
	private static Victor winch;

	public static Climber instance;
	
	public static Climber getInstance()
    {
		if (instance == null)
		   instance = new Climber();
	
		return instance;
    }
	
	public Climber(){
		winch = new Victor(RobotPorts.CLIMBER);
	}
	
	public static void setPower(double speed){
		winch.set(speed);
	}

	@Override
	protected void initDefaultCommand() {
		
	}
}
