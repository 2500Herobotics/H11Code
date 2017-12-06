package vision;

import java.util.ArrayList;

import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.vision.VisionThread;

public class Vision {
	private static final String WEBCAM_PATH = "/dev/v41/by-path/platform-ci_hdrc.0-usb-0:1.2:1.0-video-index0";

	public double centerX = 0.0;
	public double centerY = 0.0;
	
	public double width = 0.0;
	public double height = 0.0;
	
	public final Object visionLock = new Object();

	public Vision(String camera,int xResilution, int yResilution){
		//camera = "cam1"
		//xResilution = 640
		//yResilution = 480
		UsbCamera cam = CameraServer.getInstance().startAutomaticCapture(camera,0);
		cam.setResolution(xResilution, yResilution);
		
		createVisionThread(cam);
	}
	
	private void createVisionThread(UsbCamera cam){
		VisionThread visionThread = new VisionThread(cam, new GripPipeline(), pipeline->{
			ArrayList<MatOfPoint> contours = pipeline.filterContoursOutput();
			//Only runs code if there is actualy a contour to run it on
			if(contours.size() != 0){
				//This is the biggest contour
				MatOfPoint big = new MatOfPoint();
				
				//For each contour test if its area is the biggest one yet if it is then set it as the biggest
				for(MatOfPoint contour : contours){
					if(contour.width() * contour.height() > big.width() * big.height()){
						big = contour;
					}
				}
				
				Rect boundingBox = Imgproc.boundingRect(big);
				
				double width = boundingBox.width;
				double height = boundingBox.height;
				
				// ~ centor for exact do calc
				double centerX = (boundingBox.x) + (width);
				double centerY = (boundingBox.y) + (height);
				
				synchronized(visionLock){
					this.centerX = centerX;
					this.centerY = centerY;
					this.width = width;
					this.height = height;
				}
			}
		});
		
		visionThread.start();
		
		//using local vars to keep code running
		double centerX = 0, centerY = 0, width = 0, height = 0;
		synchronized(visionLock){
			centerX = this.centerX;
			centerY = this.centerY;

			width = this.width;
			height = this.height;
		}
	}

	public double getCentorX(){
		return centerX;
	}
	
	public double getCentorY(){
		return centerY;
	}
	
	public double getWidth(){
		return width;
	}
	
	public double getHeight(){
		return height;
	}
}