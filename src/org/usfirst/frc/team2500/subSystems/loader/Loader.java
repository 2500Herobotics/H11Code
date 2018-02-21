package org.usfirst.frc.team2500.subSystems.loader;

import org.usfirst.frc.team2500.robot.RobotPorts;
import org.usfirst.frc.team2500.subSystems.climber.Climber;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Loader extends Subsystem {
	
	private Victor wheels;
	private Solenoid arms;
	
	private boolean armTarget;

	public static Loader instance;
	
	public static Loader getInstance()
    {
		if (instance == null)
		   instance = new Loader();
	
		return instance;
    }
	
	public Loader(){
		wheels = new Victor(RobotPorts.INTAKE_WHEELS);
		arms = new Solenoid(RobotPorts.INTAKE_ARM);
		armTarget = false;
	}
	
	public void setPower(double speed){
		wheels.set(speed * 0.99999);
	}
	
	public void toggleArm(){
		armTarget = !armTarget;
		arms.set(armTarget);
	}
	
	public void setArm(boolean target){
		armTarget = target;
		arms.set(target);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
}
