package org.usfirst.frc.team2500.driverStation;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Controller {

	public static final int PORT_DRIVER_CONTROLLER = 0;
	
	private static Joystick pilot;
	
	public static final int BUTTON_COUNT = 6;
	private static Boolean[] pilot_Toggle;
	
	public static boolean initialized = false;
    
    public static void initialize() {
    	
    	if (initialized)
    		return;
    	
    	pilot = new Joystick(PORT_DRIVER_CONTROLLER);
    	
    	//creating arrays for the button toggles
    	pilot_Toggle = new Boolean[BUTTON_COUNT];
    	for(int i = 0; i < BUTTON_COUNT; i++){
    		pilot_Toggle[i] = false;
    	}
    	
    	createDriverstaion();
    	
    	new JoystickButton(pilot,0);
    	
    	initialized = true;
    }
    
    public static double Pilot_Throttle (){
    	double value = handleDeadband(pilot.getRawAxis(GamePad.Axis.LEFT_Y),0.1);
    	SmartDashboard.putNumber("Pilot Throttle",value);
    	return value;
    }
    
    public static double Pilot_Steering (){
    	double value = handleDeadband(pilot.getRawAxis(GamePad.Axis.RIGHT_X),0.1);
    	SmartDashboard.putNumber("Pilot Steering",value);
    	return value;
    }
    
    public static boolean Pilot_Shift(){
    	return pilot.getRawButton(2);
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