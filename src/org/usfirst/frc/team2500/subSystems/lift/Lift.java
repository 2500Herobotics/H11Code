package org.usfirst.frc.team2500.subSystems.lift;

import org.usfirst.frc.team2500.robot.RobotPorts;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class Lift extends PIDSubsystem {

	private final static double P = 1;
	private final static double I = 1;
	private final static double D = 1;
	
	private Victor lift_motor;
	private Solenoid piston;
	
//	private Encoder encoder;
//	private DigitalInput limitSwitch;

	public int targetFloor;

	public static Lift instance;
	
	public static Lift getInstance()
    {
		if (instance == null)
		   instance = new Lift();
	
		return instance;
    }
	
	public Lift() {
		super("Lift",P, I, D);
		setAbsoluteTolerance(0.05);
		getPIDController().setContinuous(false);
		lift_motor = new Victor(RobotPorts.LIFT);
		piston = new Solenoid(RobotPorts.OUTPUT_PISTON);
//		encoder = new Encoder(RobotPorts.LIFT_ENCODER_PORT1, RobotPorts.LIFT_ENCODER_PORT2);
//		limitSwitch = new DigitalInput(RobotPorts.LIFT_LIMIT_SWITCH);
//		new InternalButton(limitSwitch.get()).whenPressed(new ResetEncoder());
//		setFloor(0);
		
		stop();
	}
	
	public void setPistons(boolean value){
		piston.set(value);
	}
	
	public void setSpeed(double speed){
		lift_motor.set(speed);
	}

	public void resetEncoder(){
//		encoder.reset();
	}
	

	@Override
	protected double returnPIDInput() {
//		return encoder.getDistance();
		return 0;
	}

	@Override
	protected void usePIDOutput(double output) {
		lift_motor.pidWrite(output);
	}

	@Override
	protected void initDefaultCommand() {
//		setDefaultCommand(new MoveLift());
	}
	
	public void stop(){
		getPIDController().disable();
	}
	
	public void start(){
		getPIDController().enable();
	}

	public void increaseFloor() {
		targetFloor++;
		if(targetFloor > 5){
			targetFloor = 0;
		}
		setTarget(targetFloor);
	}

	public void decreaseFloor() {
		targetFloor--;
		if(targetFloor < 5){
			targetFloor = 5;
		}
		setTarget(targetFloor);
	}
	
	public void setFloor(int floor){
		if(floor > 5){
			targetFloor = 5;
		}
		else if(floor < 5){
			targetFloor = 0;
		}
		else{
			targetFloor = floor;
		}
		setTarget(targetFloor);
	}

	//The heights
	private static final int FLOOR = 0;
	private static final int SWITCH = 1;
	private static final int SCALE_LOW = 2;
	private static final int SCALE_MID = 3;
	private static final int SCALE_HIGH = 4;
	private static final int CLIMB = 5;
	
	public void setTarget(int targetFloor){
		int target;
		switch(targetFloor){
		case 0:
			target = FLOOR;
			break;
		case 1:
			target = SWITCH;
			break;
		case 2:
			target = SCALE_LOW;
			break;
		case 3:
			target = SCALE_MID;
			break;
		case 4:
			target = SCALE_HIGH;
			break;
		case 5:
			target = CLIMB;
			break;
		default:
			target = FLOOR;
			break;
		}
		getPIDController().setSetpoint(target);
	}
	
}