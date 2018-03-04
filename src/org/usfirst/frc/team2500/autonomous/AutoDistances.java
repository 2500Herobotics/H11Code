package org.usfirst.frc.team2500.autonomous;

public class AutoDistances {
	/*
	 * All in inches
	 */
	
    //Base line stuff
    public static final double BASE_LINE_DIST = 130; //120 But we want to go over

    //All command stuff
    //Get off of the wall so we can turn
    public static final double OFF_WALL_DIST = 10;

    //Left and right start stuff
    //Distence to drive till we get to the point that we can pick what side of the scale to go to
    public static final double SCALE_INTERSECTION_DIST = 235;
    //Distence to cross the field (this should put us in the spot as if we started auto on the other side of the field)
    public static final double SCALE_CROSS_DIST = 220;
    //Distance to line with the scale from our intersection point
    public static final double SCALE_ALIGN_DIST = 81.5;
    //Distance to drive forword to have the block above te scale
    public static final double SCALE_DIST = 32;

    //Centor start stuff
    //Degrees to turn for the left side
    public static final double SWITCH_ALIGN_DEG_LEFT = -55;
    //Degrees to turn for the right side
    public static final double SWITCH_ALIGN_DEG_RIGHT = 55;
    //Distence to turn to line up with the centor of our target switch
    public static final double SWITCH_ALIGN_DIST_LEFT = 75;
    public static final double SWITCH_ALIGN_DIST_RIGHT = 93;
    //Distence to get the block above the switch
    public static final double TO_SWITCH_DIST = 75;
    
}
