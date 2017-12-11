package org.usfirst.frc.team2500.robot;

import edu.wpi.first.wpilibj.Joystick;

public class Controller {

	public static final int PORT_DRIVER_CONTROLLER = 0;
	public static final int PORT_COPILOT_CONTROLLER = 1;
	
	private static Joystick pilot;
	private static Joystick coPilot;
	
	private static Boolean CoPilotTakeOver; 
	
	public static boolean initialized = false;
    
    public static void initialize() {
    	
    	if (initialized)
    		return;
    	
    	pilot = new Joystick(PORT_DRIVER_CONTROLLER);
    	coPilot = new Joystick(PORT_COPILOT_CONTROLLER);
    	
    	CoPilotTakeOver = false;
    	
    	initialized = true;
    }
    
    public static void SwapPilot(){
    	Joystick temp = pilot;
    	pilot = coPilot;
    	coPilot = temp;
    }

    public static double Pilot_Throttle (){
    	return handleDeadband(pilot.getRawAxis(0),0.1);
    }

    public static double Pilot_Steering (){
    	return handleDeadband(pilot.getRawAxis(1),0.1);
    }

    public static double CoPilot_Throttle (){
    	return handleDeadband(coPilot.getRawAxis(0),0.1);
    }

    public static double CoPilot_Steering (){
    	return handleDeadband(coPilot.getRawAxis(1),0.1);
    }
	
	public static double handleDeadband(double val, double deadband) {
        return (Math.abs(val) > Math.abs(deadband)) ? val : 0.0;
	}
}