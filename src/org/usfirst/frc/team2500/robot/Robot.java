package org.usfirst.frc.team2500.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2500.autonomous.AutoBaseLine;
import org.usfirst.frc.team2500.autonomous.AutoCenter;
import org.usfirst.frc.team2500.autonomous.AutoLeft;
import org.usfirst.frc.team2500.autonomous.AutoRight;
import org.usfirst.frc.team2500.driverStation.Controller;
import org.usfirst.frc.team2500.driverStation.DashboardUpdater;
import org.usfirst.frc.team2500.subSystems.chassis.LowGear;
import org.usfirst.frc.team2500.subSystems.lift.Lift;
import org.usfirst.frc.team2500.subSystems.loader.OpenClaw;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	//Things for the dashboard for picking auto
	Command autonomousCommand;
	SendableChooser<String> autonomousChooser = new SendableChooser<>();
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		//Adds a box in the dropdown for each auto mode (default auto if none picked is baseline)
		autonomousChooser.addDefault("Base Line", "Base Line");
		autonomousChooser.addObject("Left Switch", "Left Switch");
		autonomousChooser.addObject("Center", "Center");
		autonomousChooser.addObject("Right Switch", "Right Switch");
		autonomousChooser.addObject("Testing Command", "Test");
		SmartDashboard.putData("Auto mode", autonomousChooser);
		
		new DashboardUpdater().start();
	}
	
	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		
	}
	
	@Override
	public void autonomousInit() {
		new LowGear();
		//Check what auto is picked
		switch(autonomousChooser.getSelected()){
		case "Base Line":
			autonomousCommand = new AutoBaseLine();
			break;
		case "Left Switch":
			autonomousCommand = new AutoLeft();
			break;
		case "Center":
			autonomousCommand = new AutoCenter();
			break;
		case "Right Switch":
			autonomousCommand = new AutoRight();
			break;
		case "Test":
			autonomousCommand = new OpenClaw();
			break;
		default:
			autonomousCommand = new AutoBaseLine();
			break;
		}

		//Start running whatever auto is picked
		if (autonomousCommand != null){
			autonomousCommand.start();
		}
		else{
			System.out.println("Something went wrong in starting auto");
		}
	}
	
	@Override
	public void autonomousPeriodic() {
		//Run auto
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when teleop starts running.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Lift.getInstance().setSpeed(Controller.getInstance().get_Triggers());
		Scheduler.getInstance().run();
	}
}
