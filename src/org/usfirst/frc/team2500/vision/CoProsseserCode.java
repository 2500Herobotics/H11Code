package org.usfirst.frc.team2500.vision;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.cscore.*;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class CoProsseserCode {
	
	public static void main(String[] args) {
	    // Loads our OpenCV library. This MUST be included
	    System.loadLibrary("opencv_java310");
	    
	    // This is the network port you want to stream the raw received image to
	    // By rules, this has to be between 1180 and 1190, so 1185 is a good choice
	    int streamPort = 1185;
	    
	    // This stores our reference to our mjpeg server for streaming the input image
	    MjpegServer inputStream = new MjpegServer("MJPEG Server", streamPort);
	    
	    // USB Camera
	    /*
	    // This gets the image from a USB camera 
	    // Usually this will be on device 0, but there are other overloads
	    // that can be used
	    */
	    UsbCamera camera = setUsbCamera(0, inputStream);
	    // Set the resolution for our camera, since this is over USB
	    camera.setResolution(640,480);
	    
	    // This creates a CvSink for us to use. This grabs images from our selected camera, 
	    // and will allow us to use those images in opencv
	    CvSink imageSink = new CvSink("CV Image Grabber");
	    imageSink.setSource(camera);
	    
	    // This creates a CvSource to use. This will take in a Mat image that has had OpenCV operations
	    // operations 
	    CvSource imageSource = new CvSource("CV Image Source", VideoMode.PixelFormat.kMJPEG, 640, 480, 30);
	    MjpegServer cvStream = new MjpegServer("CV Image Stream", 1186);
	    cvStream.setSource(imageSource);
	    
	    // All Mats and Lists should be stored outside the loop to avoid allocations
	    // as they are expensive to create
	    Mat inputImage = new Mat();
	    
		//Outputs
		Mat hsvThresholdOutput = new Mat();
		ArrayList<MatOfPoint> findContoursOutput = new ArrayList<MatOfPoint>();
		ArrayList<MatOfPoint> filterContoursOutput = new ArrayList<MatOfPoint>();
	    
	    // Infinitely process image
	    while (true) {
	    	if(SmartDashboard.getBoolean("Vision_Prossesing", false)){
			    // Grab a frame. If it has a frame time of 0, there was an error.
			    // Just skip and continue
			    long frameTime = imageSink.grabFrame(inputImage);
			    if (frameTime == 0) continue;
				//color
			    //Probebly blue
				double[] hsvThresholdHue = {175.0, 250.0};
				double[] hsvThresholdSaturation = {140, 255};
				double[] hsvThresholdValue = {13, 255.0};
				hsvThreshold(inputImage, hsvThresholdHue, hsvThresholdSaturation, hsvThresholdValue, hsvThresholdOutput);
		
				// Step Find_Contours0:
				Mat findContoursInput = hsvThresholdOutput;
				boolean findContoursExternalOnly = false;
				findContours(findContoursInput, findContoursExternalOnly, findContoursOutput);
	
				filterContoursMinHeight = SmartDashboard.getNumber("Filter_Height", 1000.0);
				filterContoursMaxWidth = SmartDashboard.getNumber("Filter_Width", 1000.0);
				filterContoursMinArea = SmartDashboard.getNumber("Filter_Area", 1250.0);
				
				
				// Step Filter_Contours0:
				ArrayList<MatOfPoint> filterContoursContours = findContoursOutput;
				filterContours(filterContoursContours, filterContoursMinArea, filterContoursMinPerimeter, filterContoursMinWidth, filterContoursMaxWidth, filterContoursMinHeight, filterContoursMaxHeight, filterContoursSolidity, filterContoursMaxVertices, filterContoursMinVertices, filterContoursMinRatio, filterContoursMaxRatio, filterContoursOutput);
				
				Rect target = getRect(filterContoursOutput);
				SmartDashboard.putNumber("Centor_X", target.x + (target.width/2));
				SmartDashboard.putNumber("Centor_Y", target.y + (target.height/2));
				SmartDashboard.putNumber("Height", target.height);
				SmartDashboard.putNumber("Width", target.height);
				
				// Here is where you would write a processed image that you want to restreams
				// This will most likely be a marked up image of what the camera sees
				// For now, we are just going to stream the HSV image
				imageSource.putFrame(hsvThresholdOutput);
		    }
	    }
	}

	/**
	 * Segment an image based on hue, saturation, and value ranges.
	 *
	 * @param input The image on which to perform the HSL threshold.
	 * @param hue The min and max hue
	 * @param sat The min and max saturation
	 * @param val The min and max value
	 * @param output The image in which to store the output.
	 */
	private static void hsvThreshold(Mat input, double[] hue, double[] sat, double[] val, Mat out) {
		Imgproc.cvtColor(input, out, Imgproc.COLOR_BGR2HSV);
		Core.inRange(out, new Scalar(hue[0], sat[0], val[0]), new Scalar(hue[1], sat[1], val[1]), out);
	}

	/**
	 * Sets the values of pixels in a binary image to their distance to the nearest black pixel.
	 * @param input The image on which to perform the Distance Transform.
	 * @param type The Transform.
	 * @param maskSize the size of the mask.
	 * @param output The image in which to store the output.
	 */
	private static void findContours(Mat input, boolean externalOnly,
		List<MatOfPoint> contours) {
		Mat hierarchy = new Mat();
		contours.clear();
		int mode;
		if (externalOnly) {
			mode = Imgproc.RETR_EXTERNAL;
		}
		else {
			mode = Imgproc.RETR_LIST;
		}
		int method = Imgproc.CHAIN_APPROX_SIMPLE;
		Imgproc.findContours(input, contours, hierarchy, mode, method);
	}
	
	private static double filterContoursMinArea = 1250.0;
	private static double filterContoursMinPerimeter = 0;
	private static double filterContoursMinWidth = 0;
	private static double filterContoursMaxWidth = 1000;
	private static double filterContoursMinHeight = 0;
	private static double filterContoursMaxHeight = 1000;
	private static double[] filterContoursSolidity = {0, 100};
	private static double filterContoursMaxVertices = 1000000;
	private static double filterContoursMinVertices = 0;
	private static double filterContoursMinRatio = 0;
	private static double filterContoursMaxRatio = 1000;
	/**
	 * Filters out contours that do not meet certain criteria.
	 * @param inputContours is the input list of contours
	 * @param output is the the output list of contours
	 * @param minArea is the minimum area of a contour that will be kept
	 * @param minPerimeter is the minimum perimeter of a contour that will be kept
	 * @param minWidth minimum width of a contour
	 * @param maxWidth maximum width
	 * @param minHeight minimum height
	 * @param maxHeight maximimum height
	 * @param Solidity the minimum and maximum solidity of a contour
	 * @param minVertexCount minimum vertex Count of the contours
	 * @param maxVertexCount maximum vertex Count
	 * @param minRatio minimum ratio of width to height
	 * @param maxRatio maximum ratio of width to height
	 */
	private static void filterContours(List<MatOfPoint> inputContours, double minArea,
		double minPerimeter, double minWidth, double maxWidth, double minHeight, double
		maxHeight, double[] solidity, double maxVertexCount, double minVertexCount, double
		minRatio, double maxRatio, List<MatOfPoint> output) {
		final MatOfInt hull = new MatOfInt();
		output.clear();
		//operation
		for (int i = 0; i < inputContours.size(); i++) {
			final MatOfPoint contour = inputContours.get(i);
			final Rect bb = Imgproc.boundingRect(contour);
			if (bb.width < minWidth || bb.width > maxWidth) continue;
			if (bb.height < minHeight || bb.height > maxHeight) continue;
			final double area = Imgproc.contourArea(contour);
			if (area < minArea) continue;
			if (Imgproc.arcLength(new MatOfPoint2f(contour.toArray()), true) < minPerimeter) continue;
			Imgproc.convexHull(contour, hull);
			MatOfPoint mopHull = new MatOfPoint();
			mopHull.create((int) hull.size().height, 1, CvType.CV_32SC2);
			for (int j = 0; j < hull.size().height; j++) {
				int index = (int)hull.get(j, 0)[0];
				double[] point = new double[] { contour.get(index, 0)[0], contour.get(index, 0)[1]};
				mopHull.put(j, 0, point);
			}
			final double solid = 100 * area / Imgproc.contourArea(mopHull);
			if (solid < solidity[0] || solid > solidity[1]) continue;
			if (contour.rows() < minVertexCount || contour.rows() > maxVertexCount)	continue;
			final double ratio = bb.width / (double)bb.height;
			if (ratio < minRatio || ratio > maxRatio) continue;
			output.add(contour);
		}
	}
	
	private static Rect getRect(ArrayList<MatOfPoint> contours){
		if(contours.size() != 0){
			MatOfPoint big = new MatOfPoint();
			for(MatOfPoint contour : contours){
				if(contour.width() * contour.height() > big.width() * big.height()){
					big = contour;
				}
			}
			return Imgproc.boundingRect(big);
		}
		return new Rect();
	}
	
	private static UsbCamera setUsbCamera(int cameraId, MjpegServer server) {
		// This gets the image from a USB camera 
		// Usually this will be on device 0, but there are other overloads
		// that can be used
		UsbCamera camera = new UsbCamera("CoprocessorCamera", cameraId);
		server.setSource(camera);
		return camera;
	}
}