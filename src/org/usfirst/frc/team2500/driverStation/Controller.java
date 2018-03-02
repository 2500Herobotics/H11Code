package org.usfirst.frc.team2500.driverStation;

import org.usfirst.frc.team2500.subSystems.chassis.ShiftCommand;
import org.usfirst.frc.team2500.subSystems.loader.ToggleClaw;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Controller {

	public static final int PORT_DRIVER_CONTROLLER = 0;
	
	private static Joystick pilot;
	
	public static boolean initialized = false;
    
	//Setup all of the joystick commands and ports
    public static void initialize() {
    	
    	if (initialized)
    		return;
    	
    	pilot = new Joystick(PORT_DRIVER_CONTROLLER);
    	
    	createDriverstaion();

    	new JoystickButton(pilot,GamePad.A).whenPressed(new ToggleClaw());
    	new JoystickButton(pilot,GamePad.B).whenPressed(new ShiftCommand());
    	
    	initialized = true;
    }
    
    //Return the value you get from subtracing the trigers
    public static double Get_Triggers(){
    	return pilot.getRawAxis(2) - pilot.getRawAxis(3);
    }
	
    //Function to remove the controler not homing properly
	public static double handleDeadband(double val, double deadband) {
		if (Math.abs(val) > deadband){
			return val;
		}
		return 0;
	}
	
	//make the spots on the dashboard
	private static void createDriverstaion(){
    	SmartDashboard.putNumber("pilotThrottle",0);
    	SmartDashboard.putNumber("pilotSteering",0);
    	SmartDashboard.putNumber("leftSpeed",0);
    	SmartDashboard.putNumber("rightSpeed",0);
    	SmartDashboard.putNumber("leftDist",0);
    	SmartDashboard.putNumber("rightDist",0);
    	SmartDashboard.putNumber("rotation",0);
		
	}

//	public static boolean Get_X() {
//		return pilot.getRawButton(GamePad.X);
//	}

	//Retunr the proper x and update the dashboard with it
	public static double getTurn() {
		double turn = pilot.getRawAxis(1) * -1;
    	SmartDashboard.putNumber("pilotSteering",turn);
		return turn;
	}

	//Retunr the proper y and update the dashboard with it
	public static double getMove() {
		double move = pilot.getRawAxis(0);
    	SmartDashboard.putNumber("pilotThrottle",move);
		return move;
	}
}