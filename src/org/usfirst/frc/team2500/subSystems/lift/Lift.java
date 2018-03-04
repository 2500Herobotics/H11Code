package org.usfirst.frc.team2500.subSystems.lift;

import org.usfirst.frc.team2500.robot.RobotPorts;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Lift extends Subsystem {
	
	private Victor lift_motor;
	
	public int targetFloor;

	public static Lift instance;
	
	public static Lift getInstance()
    {
		if (instance == null)
		   instance = new Lift();
	
		return instance;
    }
	
	public Lift() {
		lift_motor = new Victor(RobotPorts.LIFT);
	}
	
	public void setSpeed(double speed){
		lift_motor.set(speed);
	}

	@Override
	protected void initDefaultCommand() {
//		setDefaultCommand(new RunLift());
	}
}