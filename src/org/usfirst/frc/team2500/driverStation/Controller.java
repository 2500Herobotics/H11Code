package org.usfirst.frc.team2500.driverStation;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

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
    	NetworkTable.getTable("SmartDashboard").putNumber("pilotThrottle",value);
    	return value;
    }
    
    public static double Pilot_Steering (){
    	double value = handleDeadband(pilot.getRawAxis(GamePad.Axis.RIGHT_X),0.1);
    	NetworkTable.getTable("SmartDashboard").putNumber("pilotSteering",value);
    	return value;
    }
    
    public static double CoPilot_Throttle (){
    	double value = handleDeadband(coPilot.getRawAxis(GamePad.Axis.LEFT_Y),0.1);
    	NetworkTable.getTable("SmartDashboard").putNumber("coPilotThrottle",value);
    	return value;
    }
    
    public static double CoPilot_Steering (){
    	double value = handleDeadband(coPilot.getRawAxis(GamePad.Axis.RIGHT_X),0.1);
    	NetworkTable.getTable("SmartDashboard").putNumber("coPilotSteering",value);
    	return value;
    }
    
	private static Boolean[] pilot_Last_Buttons;
	private static Boolean[] coPilot_Last_Buttons;
    
    public static void Toggle_Buttons(){
    	Boolean[] pilot_Current_Buttons = new Boolean[BUTTON_COUNT];
    	for(int i = 0; i < BUTTON_COUNT; i++){
    		pilot_Current_Buttons[i] = pilot.getRawButton(i);
    	}
		for(int i = 0; i < BUTTON_COUNT; i++){
			if(pilot_Current_Buttons != pilot_Last_Buttons){
				pilot_Last_Buttons[i] = !pilot_Last_Buttons[i];
			}
    	}
		
    	Boolean[] coPilot_Current_Buttons = new Boolean[BUTTON_COUNT];
    	for(int i = 0; i < BUTTON_COUNT; i++){
    		coPilot_Current_Buttons[i] = coPilot.getRawButton(i);
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
    	NetworkTable.getTable("SmartDashboard").putNumber("pilotThrottle",0);
    	NetworkTable.getTable("SmartDashboard").putNumber("pilotSteering",0);
    	NetworkTable.getTable("SmartDashboard").putNumber("coPilotThrottle",0);
    	NetworkTable.getTable("SmartDashboard").putNumber("coPilotSteering",0);

    	NetworkTable.getTable("SmartDashboard").putBoolean("CoPilotTakeOver",CoPilotTakeOver);

    	NetworkTable.getTable("SmartDashboard").putBoolean("Pilot A",false);
    	NetworkTable.getTable("SmartDashboard").putBoolean("Pilot B",false);
    	NetworkTable.getTable("SmartDashboard").putBoolean("Pilot X",false);
    	NetworkTable.getTable("SmartDashboard").putBoolean("Pilot Y",false);
    	NetworkTable.getTable("SmartDashboard").putBoolean("Pilot LB",false);
    	NetworkTable.getTable("SmartDashboard").putBoolean("Pilot RB",false);

    	NetworkTable.getTable("SmartDashboard").putBoolean("Pilot A Toggled",false);
    	NetworkTable.getTable("SmartDashboard").putBoolean("Pilot B Toggled",false);
    	NetworkTable.getTable("SmartDashboard").putBoolean("Pilot X Toggled",false);
    	NetworkTable.getTable("SmartDashboard").putBoolean("Pilot Y Toggled",false);
    	NetworkTable.getTable("SmartDashboard").putBoolean("Pilot LB Toggled",false);
    	NetworkTable.getTable("SmartDashboard").putBoolean("Pilot RB Toggled",false);

    	NetworkTable.getTable("SmartDashboard").putBoolean("CoPilot A",false);
    	NetworkTable.getTable("SmartDashboard").putBoolean("CoPilot B",false);
    	NetworkTable.getTable("SmartDashboard").putBoolean("CoPilot X",false);
    	NetworkTable.getTable("SmartDashboard").putBoolean("CoPilot Y",false);
    	NetworkTable.getTable("SmartDashboard").putBoolean("CoPilot LB",false);
    	NetworkTable.getTable("SmartDashboard").putBoolean("CoPilot RB",false);

    	NetworkTable.getTable("SmartDashboard").putBoolean("CoPilot A Toggled",false);
    	NetworkTable.getTable("SmartDashboard").putBoolean("CoPilot B Toggled",false);
    	NetworkTable.getTable("SmartDashboard").putBoolean("CoPilot X Toggled",false);
    	NetworkTable.getTable("SmartDashboard").putBoolean("CoPilot Y Toggled",false);
    	NetworkTable.getTable("SmartDashboard").putBoolean("CoPilot LB Toggled",false);
    	NetworkTable.getTable("SmartDashboard").putBoolean("CoPilot RB Toggled",false);
	}
}