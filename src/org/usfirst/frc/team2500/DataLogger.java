package org.usfirst.frc.team2500;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.usfirst.frc.team2500.driverStation.Controller;
import org.usfirst.frc.team2500.robot.Chassis;
import org.usfirst.frc.team2500.vision.CoProsseserInteracion;

public class DataLogger {

	private static File f;
	private static BufferedWriter bw;
	private static FileWriter fw;
	
	public static void initialize(){
		String fileName = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new java.util.Date());
    	try {
    		//Path for flashedrive
    		f = new File("/media/sda1/" + fileName + ".txt");
    		if(!f.exists()){
    			f.createNewFile();
    		}
			fw = new FileWriter(f, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	bw = new BufferedWriter(fw);
    }
    
    public static void closeFileWriter(){
    	try{
			bw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }


    public static void logData(){
    	logTime();
    	logDriveEncoders();
    	logJoySticks();
    	logButtons();
    	logVision();
    	try {
			bw.append("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    private static void logTime(){
    	try {
			bw.append(Long.toString(System.currentTimeMillis()) + " ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private static void logDriveEncoders(){
    	try {
    		//Log the rate
			bw.append(Double.toString(Chassis.getLeftRate()) + " ");
			bw.append(Double.toString(Chassis.getRightRate()) + " ");
			//Log the Distence
			bw.append(Double.toString(Chassis.getRightDist()) + " ");
			bw.append(Double.toString(Chassis.getLeftDist()) + " ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private static void logJoySticks(){
    	try {
    		//Log the pilots joysticks
			bw.append(Double.toString(Controller.Pilot_Steering()) + " ");
			bw.append(Double.toString(Controller.Pilot_Throttle()) + " ");
			//Log the coPilots joysticks
			bw.append(Double.toString(Controller.CoPilot_Steering()) + " ");
			bw.append(Double.toString(Controller.CoPilot_Throttle()) + " ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private static void logButtons(){
    	try {
			bw.append(" ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private static void logVision(){
    	try {
			bw.append(Double.toString(CoProsseserInteracion.getCentorX()) + " ");
			bw.append(Double.toString(CoProsseserInteracion.getCentorY()) + " ");
			bw.append(Double.toString(CoProsseserInteracion.getWidth()) + " ");
			bw.append(Double.toString(CoProsseserInteracion.getHeight()) + " ");
			bw.append(Double.toString(CoProsseserInteracion.getArea()) + " ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
