package org.usfirst.frc.team2500;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2500.autonomus.AutoTemplate;
import org.usfirst.frc.team2500.driverStation.Controller;
import org.usfirst.frc.team2500.teleops.competitionTeleop;
import org.usfirst.frc.team2500.teleops.outreachTeleop;
import org.usfirst.frc.team2500.robot.Chassis;
import org.usfirst.frc.team2500.robot.Unloader;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Main extends IterativeRobot {

	Command autonomousCommand;
	SendableChooser<Command> autonomousChooser = new SendableChooser<>();
	
	Command teleopCommand;
	SendableChooser<Command> teleopChooser = new SendableChooser<>();
	
	UsbCamera camera1;
	UsbCamera camera2;
	
	//CustomCameraServer server;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		// Connect NetworkTables, and get access to the publishing table
	    NetworkTable.setClientMode();
	    // Set your team number here
	    NetworkTable.setTeam(2500);
	    
		NetworkTable.initialize();
		NetworkTable.setIPAddress("10.25.00.2");
		
		Controller.initialize();

		Chassis.initialize();
		
		Unloader.initialize();
		
		autonomousChooser.addDefault("Default Auto", new AutoTemplate());
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", autonomousChooser);
		
		teleopChooser.addDefault("Competition Auto", new competitionTeleop());
		teleopChooser.addObject("My Auto", new outreachTeleop());
		SmartDashboard.putData("Teleop Mode", teleopChooser);

		camera1 = CameraServer.getInstance().startAutomaticCapture(0);
		camera2 = CameraServer.getInstance().startAutomaticCapture(1);
		//server = CameraServer.getInstance().GetServer();
	}
	
	public void  robotPeriodic(){
		
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		DataLogger.closeFileWriter();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}
	
	@Override
	public void autonomousInit() {
		autonomousCommand = autonomousChooser.getSelected();

		// schedule the autonomous command (example)
		if (autonomousCommand != null){
			autonomousCommand.start();
		}
		else{
			System.out.println("Something went wrong in starting auto");
		}
	}
	
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();

		teleopCommand = teleopChooser.getSelected();

		// schedule the autonomous command (example)
		if (teleopCommand != null)
			teleopCommand.start();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		//Test each button to see if it was releced
		Controller.Toggle_Buttons();
		
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
