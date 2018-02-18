package org.usfirst.frc.team2500.driverStation;

import org.usfirst.frc.team2500.subSystems.chassis.ShiftCommand;
import org.usfirst.frc.team2500.subSystems.loader.LoadBlock;
import org.usfirst.frc.team2500.subSystems.loader.UnloadBlock;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Controller {

	public static final int PORT_DRIVER_CONTROLLER = 0;
	
	private static Joystick pilot;
	
	public static boolean initialized = false;
    
    public static void initialize() {
    	
    	if (initialized)
    		return;
    	
    	pilot = new Joystick(PORT_DRIVER_CONTROLLER);
    	
    	createDriverstaion();

    	new JoystickButton(pilot,GamePad.B).whenPressed(new ShiftCommand());
    	new JoystickButton(pilot,GamePad.LB).whileHeld(new LoadBlock());
    	new JoystickButton(pilot,GamePad.RB).whileHeld(new UnloadBlock());
    	
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
		
	}
}