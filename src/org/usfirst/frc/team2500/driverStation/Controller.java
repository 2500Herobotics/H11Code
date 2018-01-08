package org.usfirst.frc.team2500.driverStation;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
    	
    	createDriverstaion();
    	
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
    	double value = handleDeadband(pilot.getRawAxis(GamePad.Axis.LEFT_Y),0.1);
//    	SmartDashboard.putNumber("pilotThrottle",value);
    	return value;
    }
    
    public static double Pilot_Steering (){
    	double value = handleDeadband(pilot.getRawAxis(GamePad.Axis.RIGHT_X),0.1);
//    	SmartDashboard.putNumber("pilotSteering",value);
    	return value;
    }
    
    public static double CoPilot_Throttle (){
    	double value = handleDeadband(coPilot.getRawAxis(GamePad.Axis.LEFT_Y),0.1);
//    	SmartDashboard.putNumber("coPilotThrottle",value);
    	return value;
    }
    
    public static double CoPilot_Steering (){
    	double value = handleDeadband(coPilot.getRawAxis(GamePad.Axis.RIGHT_X),0.1);
//    	SmartDashboard.putNumber("coPilotSteering",value);
    	return value;
    }
    
	private static Boolean[] pilot_Last_Buttons;
	private static Boolean[] coPilot_Last_Buttons;
    
    public static void Toggle_Buttons(){
    	Boolean[] pilot_Current_Buttons = new Boolean[BUTTON_COUNT];
    	for(int i = 0; i < BUTTON_COUNT; i++){
    		pilot_Current_Buttons[i] = pilot.getRawButton(i + 1);
    	}
		for(int i = 0; i < BUTTON_COUNT; i++){
			if(pilot_Current_Buttons != pilot_Last_Buttons){
				pilot_Last_Buttons[i] = !pilot_Last_Buttons[i];
			}
    	}
		
    	Boolean[] coPilot_Current_Buttons = new Boolean[BUTTON_COUNT];
    	for(int i = 0; i < BUTTON_COUNT; i++){
    		coPilot_Current_Buttons[i] = coPilot.getRawButton(i + 1);
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
	
	private static void createDriverstaion(){
//    	SmartDashboard.putNumber("pilotThrottle",0);
//    	SmartDashboard.putNumber("pilotSteering",0);
//    	SmartDashboard.putNumber("coPilotThrottle",0);
//    	SmartDashboard.putNumber("coPilotSteering",0);
//
//    	SmartDashboard.putBoolean("CoPilotTakeOver",CoPilotTakeOver);
//
//    	SmartDashboard.putBoolean("Pilot A",false);
//    	SmartDashboard.putBoolean("Pilot B",false);
//    	SmartDashboard.putBoolean("Pilot X",false);
//    	SmartDashboard.putBoolean("Pilot Y",false);
//    	SmartDashboard.putBoolean("Pilot LB",false);
//    	SmartDashboard.putBoolean("Pilot RB",false);
//
//    	SmartDashboard.putBoolean("Pilot A Toggled",false);
//    	SmartDashboard.putBoolean("Pilot B Toggled",false);
//    	SmartDashboard.putBoolean("Pilot X Toggled",false);
//    	SmartDashboard.putBoolean("Pilot Y Toggled",false);
//    	SmartDashboard.putBoolean("Pilot LB Toggled",false);
//    	SmartDashboard.putBoolean("Pilot RB Toggled",false);
//
//    	SmartDashboard.putBoolean("CoPilot A",false);
//    	SmartDashboard.putBoolean("CoPilot B",false);
//    	SmartDashboard.putBoolean("CoPilot X",false);
//    	SmartDashboard.putBoolean("CoPilot Y",false);
//    	SmartDashboard.putBoolean("CoPilot LB",false);
//    	SmartDashboard.putBoolean("CoPilot RB",false);
//
//    	SmartDashboard.putBoolean("CoPilot A Toggled",false);
//    	SmartDashboard.putBoolean("CoPilot B Toggled",false);
//    	SmartDashboard.putBoolean("CoPilot X Toggled",false);
//    	SmartDashboard.putBoolean("CoPilot Y Toggled",false);
//    	SmartDashboard.putBoolean("CoPilot LB Toggled",false);
//    	SmartDashboard.putBoolean("CoPilot RB Toggled",false);
	}
}