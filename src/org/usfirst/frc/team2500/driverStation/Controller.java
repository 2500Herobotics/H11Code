package org.usfirst.frc.team2500.driverStation;

import org.usfirst.frc.team2500.subSystems.chassis.Chassis;
import org.usfirst.frc.team2500.subSystems.chassis.ShiftCommand;
import org.usfirst.frc.team2500.subSystems.loader.ToggleClaw;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Controller {

	public static final int PORT_DRIVER_CONTROLLER = 0;
	
	private Joystick pilot;
	
	public static Controller instance;
	
	public static Controller getInstance()
    {
		if (instance == null)
		   instance = new Controller();
	
		return instance;
    }
	
	public Controller(){
    	pilot = new Joystick(PORT_DRIVER_CONTROLLER);
    	
    	createDriverstaion();

    	new JoystickButton(pilot,GamePad.A).whenPressed(new ToggleClaw());
    	new JoystickButton(pilot,GamePad.B).whenPressed(new ShiftCommand());
	}
    
    //Return the value you get from subtracing the trigers
    public double get_Triggers(){
    	double value = pilot.getRawAxis(2) - pilot.getRawAxis(3);
    	SmartDashboard.putNumber("Lift Speed",value);
    	return value;
    }
	
    //Function to remove the controler not homing properly
	public double handleDeadband(double val, double deadband) {
		if (Math.abs(val) > deadband){
			return val;
		}
		return 0;
	}
	
	//make the spots on the dashboard
	private void createDriverstaion(){
    	SmartDashboard.putNumber("Lift Speed",0);
    	SmartDashboard.putNumber("Pilot Throttle",0);
    	SmartDashboard.putNumber("Pilot Steering",0);
    	SmartDashboard.putNumber("Left Speed",0);
    	SmartDashboard.putNumber("Right Speed",0);
    	SmartDashboard.putNumber("Left Dist",0);
    	SmartDashboard.putNumber("Right Dist",0);
    	SmartDashboard.putNumber("Rotation",0);
	}

	//Retunr the proper x and update the dashboard with it
	public double getTurn() {
		double turn = pilot.getRawAxis(1) * -1;
		return turn;
	}

	//Retunr the proper y and update the dashboard with it
	public double getMove() {
		double move = pilot.getRawAxis(0);
		return move;
	}
}