package org.usfirst.frc.team2500.vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CoProsseserInteracion {
	
	private static NetworkTable table;
	
	public static void initialize(){
		
		//NetworkTableInstance.create();
		//NetworkTable
		//table = NetworkTable.getTable("GRIP/myContoursReport");
	}
	
	public static double getCentorX(){
		return SmartDashboard.getNumber("Centor_X", 960);
	}
	
	public static double getCentorY(){
		return SmartDashboard.getNumber("Centor_Y", 540);
	}
	
	public static double getHeight(){
		return SmartDashboard.getNumber("Height", 0);
	}
	
	public static double getWidth(){
		return SmartDashboard.getNumber("Width", 0);
	}
	
	public static double getArea(){
		return getHeight() * getWidth();
	}
	
	public static void camerasOn(boolean value){
		SmartDashboard.putBoolean("Cameras_On", value);
	}
	
	public static void VisionProssesingOn(boolean value){
		if(!SmartDashboard.getBoolean("Cameras_On", false) && value){
			camerasOn(true);
		}
		SmartDashboard.putBoolean("Vision_Prossesing", value);
	}
	
	public static void setFilterHeight(double height){
		SmartDashboard.putNumber("Filter_Height", height);
	}
	
	public static void setFilterWidth(double width){
		SmartDashboard.putNumber("Filter_Width", width);
	}
	
	public static void setFilterArea(double area){
		SmartDashboard.putNumber("Filter_Area", area);
	}
}
