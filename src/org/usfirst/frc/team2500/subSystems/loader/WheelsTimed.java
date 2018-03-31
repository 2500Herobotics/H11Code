package org.usfirst.frc.team2500.subSystems.loader;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class WheelsTimed extends TimedCommand {

	private double speed;
	
    public WheelsTimed(double timeout, double speed) {
        super(timeout);
        this.speed = speed;
        requires(Claw.getInstance());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Claw.getInstance().setWheels(speed);
    }

    // Called once after timeout
    protected void end() {
    	Claw.getInstance().setWheels(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Claw.getInstance().setWheels(0);
    }
}
