package org.usfirst.frc.team2500.subSystems.loader;

import org.usfirst.frc.team2500.robot.RobotPorts;
import org.usfirst.frc.team2500.subSystems.climber.Climber;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Claw extends Subsystem {
	
	private Solenoid claw;
	private Solenoid lower;
	private Victor wheel_motor;


	public static Claw instance;
	
	public static Claw getInstance()
    {
		if (instance == null)
		   instance = new Claw();
	
		return instance;
    }
	
	public Claw(){
		claw = new Solenoid(RobotPorts.INTAKE_ARM);
		lower = new Solenoid(RobotPorts.INTAKE_LOWER);
		wheel_motor = new Victor(RobotPorts.INTAKE_WHEELS);
	}
	
	public void toggleArm(){
		claw.set(!getState());
	}
	
	public void setArm(boolean target){
		claw.set(target);
	}
	
	public void setDeployed(boolean target){
		lower.set(target);
	}
	
	public void setWheels(double speed){
		wheel_motor.set(speed);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
	
	public boolean getLowered(){
		return lower.get();
	}
	
	public boolean getState(){
		return claw.get();
	}
}
