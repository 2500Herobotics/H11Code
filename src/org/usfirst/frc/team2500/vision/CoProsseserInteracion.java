package org.usfirst.frc.team2500.vision;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class CoProsseserInteracion {
	
	private static NetworkTable table;
	
	public static void initialize(){
		table = NetworkTable.getTable("Vision");
	}
	
	public static double getCentorX(){
		return table.getNumber("Centor_X", 960);
	}
	
	public static double getCentorY(){
		return table.getNumber("Centor_Y", 540);
	}
	
	public static double getHeight(){
		return table.getNumber("Height", 0);
	}
	
	public static double getWidth(){
		return table.getNumber("Width", 0);
	}
	
	public static double getArea(){
		return getHeight() * getWidth();
	}
	
	public static void camerasOn(boolean value){
		table.putBoolean("Cameras_On", value);
	}
	
	public static void VisionProssesingOn(boolean value){
		if(!table.getBoolean("Cameras_On", false) && value){
			camerasOn(true);
		}
		table.putBoolean("Vision_Prossesing", value);
	}
	
	public static void setFilterHeight(double height){
		table.putNumber("Filter_Height", height);
	}
	
	public static void setFilterWidth(double width){
		table.putNumber("Filter_Width", width);
	}
	
	public static void setFilterArea(double area){
		table.putNumber("Filter_Area", area);
	}
}
