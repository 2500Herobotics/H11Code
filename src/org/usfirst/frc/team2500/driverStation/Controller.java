package org.usfirst.frc.team2500.driverStation;

import org.usfirst.frc.team2500.subSystems.chassis.ShiftCommand;
import org.usfirst.frc.team2500.subSystems.loader.LoadBlock;
import org.usfirst.frc.team2500.subSystems.loader.StopIntake;
import org.usfirst.frc.team2500.subSystems.loader.ToggleArms;
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

    	new JoystickButton(pilot,GamePad.A).whenPressed(new ToggleArms());
    	new JoystickButton(pilot,GamePad.B).whenPressed(new ShiftCommand());
    	new JoystickButton(pilot,GamePad.LB).whileHeld(new LoadBlock());
    	new JoystickButton(pilot,GamePad.RB).whileHeld(new UnloadBlock());
    	new JoystickButton(pilot,GamePad.LB).whenReleased(new StopIntake());
    	new JoystickButton(pilot,GamePad.RB).whenReleased(new StopIntake());
    	
    	initialized = true;
    }
    
    public static double Get_Triggers(){
    	return pilot.getRawAxis(2)-pilot.getRawAxis(3);
    }
    
    public static boolean Pilot_Shift(){
    	return pilot.getRawButton(2);
    }
	
	public static double handleDeadband(double val, double deadband) {
		if (Math.abs(val) > deadband){
			return val;
		}
		return 0;
	}
	
	private static void createDriverstaion(){
//    	SmartDashboard.putNumber("pilotThrottle",0);
//    	SmartDashboard.putNumber("pilotSteering",0);
		
	}

	public static boolean Get_X() {
		return pilot.getRawButton(GamePad.X);
	}

	public static double getTurn() {
		return pilot.getRawAxis(1) * -1;
	}

	public static double getMove() {
		return pilot.getRawAxis(0);
	}
}