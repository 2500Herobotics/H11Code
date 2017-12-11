package org.usfirst.frc.team2500.robot;

import edu.wpi.first.wpilibj.Joystick;

public class Controller {

	public static final int PORT_DRIVER_CONTROLLER = 0;
	public static final int PORT_COPILOT_CONTROLLER = 1;
	
	private static Joystick pilot;
	private static Joystick coPilot;
	
	public static final int BUTTON_COUNT = 6;
	private static Boolean[] pilot_Toggle;
	private static Boolean[] coPilot_Toggle;
	
	private static Boolean CoPilotTakeOver; 
	
	public static boolean initialized = false;
    
    public static void initialize() {
    	
    	if (initialized)
    		return;
    	
    	pilot = new Joystick(PORT_DRIVER_CONTROLLER);
    	coPilot = new Joystick(PORT_COPILOT_CONTROLLER);
    	
    	//creating arrays for the button toggles
    	pilot_Toggle = new Boolean[BUTTON_COUNT];
    	for(int i = 0; i < BUTTON_COUNT; i++){
    		pilot_Toggle[i] = false;
    	}
    	coPilot_Toggle = new Boolean[BUTTON_COUNT];
    	for(int i = 0; i < BUTTON_COUNT; i++){
    		coPilot_Toggle[i] = false;
    	}
    	pilot_Last_Buttons = new Boolean[BUTTON_COUNT];
    	for(int i = 0; i < BUTTON_COUNT; i++){
    		pilot_Last_Buttons[i] = false;
    	}
    	coPilot_Last_Buttons = new Boolean[BUTTON_COUNT];
    	for(int i = 0; i < BUTTON_COUNT; i++){
    		coPilot_Last_Buttons[i] = false;
    	}
    	
    	CoPilotTakeOver = false;
    	
    	initialized = true;
    }
    
    public static void SwapPilot(){
    	Joystick temp = pilot;
    	pilot = coPilot;
    	coPilot = temp;
    	temp = null;
    	CoPilotTakeOver = !CoPilotTakeOver;
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

	private static Boolean[] pilot_Last_Buttons;
	private static Boolean[] coPilot_Last_Buttons;
    
    public static void Toggle_Buttons(){
    	Boolean[] pilot_Current_Buttons = new Boolean[BUTTON_COUNT];
    	for(int i = 0; i < BUTTON_COUNT; i++){
    		pilot_Current_Buttons[i] = pilot.getRawButton(i);
    	}
    	Boolean[] coPilot_Current_Buttons = new Boolean[BUTTON_COUNT];
    	for(int i = 0; i < BUTTON_COUNT; i++){
    		coPilot_Current_Buttons[i] = coPilot.getRawButton(i);
    	}
    	
		for(int i = 0; i < BUTTON_COUNT; i++){
			if(pilot_Current_Buttons != pilot_Last_Buttons){
				pilot_Last_Buttons[i] = !pilot_Last_Buttons[i];
			}
    	}    	
		for(int i = 0; i < BUTTON_COUNT; i++){
			if(coPilot_Current_Buttons != coPilot_Last_Buttons){
				pilot_Last_Buttons[i] = !pilot_Last_Buttons[i];
			}
    	}
    }
	
	public static double handleDeadband(double val, double deadband) {
        return (Math.abs(val) > Math.abs(deadband)) ? val : 0.0;
	}
}