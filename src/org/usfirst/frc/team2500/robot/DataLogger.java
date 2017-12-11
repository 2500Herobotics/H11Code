package org.usfirst.frc.team2500.robot;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class DataLogger {

	private static File f;
	private static BufferedWriter bw;
	private static FileWriter fw;
	
	public static void initialize(){
		String fileName = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new java.util.Date());;
    	try {
    		//Path for flashedrive
    		f = new File("/media/sda1/" + fileName + ".txt");
    		if(!f.exists()){
    			f.createNewFile();
    		}
			fw = new FileWriter(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	bw = new BufferedWriter(fw);
    }


    public static void logData(){
    	logTime();
    	logDriveEncoders();
    	logJoySticks();
    	logButtons();
    	logVision();
    	try {
			bw.append("\n");
			bw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    private static void logTime(){
    	try {
			bw.append(Long.toString(System.currentTimeMillis()) + " ");
			bw.close();
			fw.close();
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
			bw.close();
			fw.close();
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
			bw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private static void logButtons(){
    	try {
			bw.append("");
			bw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private static void logVision(){
    	try {
			bw.append("");
			bw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
