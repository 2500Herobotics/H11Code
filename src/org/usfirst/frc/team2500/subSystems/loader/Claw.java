package org.usfirst.frc.team2500.subSystems.loader;

import org.usfirst.frc.team2500.robot.RobotPorts;
import org.usfirst.frc.team2500.subSystems.climber.Climber;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Claw extends Subsystem {
	
	private Solenoid claw;
	
	private boolean clawTarget;

	public static Claw instance;
	
	public static Claw getInstance()
    {
		if (instance == null)
		   instance = new Claw();
	
		return instance;
    }
	
	public Claw(){
		claw = new Solenoid(RobotPorts.INTAKE_ARM);
		clawTarget = false;
	}
	
	public void toggleArm(){
		clawTarget = !clawTarget;
		claw.set(clawTarget);
	}
	
	public void setArm(boolean target){
		clawTarget = target;
		claw.set(target);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
}
