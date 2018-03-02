package org.usfirst.frc.team2500.subSystems.chassis;

import edu.wpi.first.wpilibj.command.Command;

public class DriveDist extends Command {
	
	private double leftDistance;
	private double rightDistance;
	
	public DriveDist(double leftDistance, double rightDistance){
		requires(Chassis.getInstance());
		this.leftDistance = leftDistance;
		this.rightDistance = rightDistance;
        setTimeout(3);
	}
	
	public void initialize(){
		Chassis.getInstance().resetEncoder();
		Chassis.getInstance().setDistance(leftDistance, rightDistance);
		Chassis.getInstance().startPID();
		System.out.println("Driving Distance: " + leftDistance + "   " + rightDistance);
	}

	@Override
	protected boolean isFinished() {
        return isTimedOut() || Chassis.getInstance().getPIDController().onTarget();
	}
}
