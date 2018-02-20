

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Chassis extends Subsystem{

	public static Chassis instance;
	
	public static Chassis getInstance()
    {
		if (instance == null)
		   instance = new Chassis();
	
		return instance;
    }

	private ChassisSide leftChassis;
	private ChassisSide rightChassis;

	private Solenoid shifter;

	private boolean shiftTarget;

	private AHRS gyro;
	
	public Chassis(){
		leftChassis = new ChassisSide(0,0,1);
		rightChassis = new ChassisSide(1,2,3);

		gyro = new AHRS(SPI.Port.kMXP);
		gyro.reset();

		shiftTarget = false;
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveChassis());
	}
	
	public void resetEncoder(){
		leftChassis.resetEncoder();
		rightChassis.resetEncoder();
	}
	
	public void setDistance(double left,double right){
		leftChassis.setTarget(left);
		rightChassis.setTarget(right * -1);
	}
	
	public void printDistace(){
		System.out.println(leftChassis.getDistance() + "   " + rightChassis.getDistance());
	}

	private final double MAX_HIGH_GEAR_SPEED = 2;
	private final double MAX_LOW_GEAR_SPEED = 1;

	private final double Min_MAX_CONVERTER = MAX_LOW_GEAR_SPEED/MAX_HIGH_GEAR_SPEED;

	private final double LOW_GEAR_SHIFT_PERCENT_HIGH = 0.9;
	private final double LOW_GEAR_SHIFT_PERCENT_LOW = 0.8;

	public void shiftingTankDrive(double left,double right){
		if(shiftTarget){
			double averageRate = (leftChassis.getRate() + rightChassis.getRate())/2;

			if(averageRate > MAX_LOW_GEAR_SPEED * LOW_GEAR_SHIFT_PERCENT_HIGH){
				shifter.set(true);
				tankDrive(left,right);
				return;
			}
			if(averageRate < MAX_LOW_GEAR_SPEED * LOW_GEAR_SHIFT_PERCENT_LOW){
				shifter.set(false);
			}

			tankDrive(left/Min_MAX_CONVERTER,right/Min_MAX_CONVERTER);
		}
		else{
			tankDrive(left,right);
		}
	}


	public void tankDrive(double left,double right){
		leftChassis.setSpeed(left);
		rightChassis.setSpeed(right);
	}

	public void arcadeDrive(double throttle, double turn) {
	    arcadeDrive(throttle, turn, true);
	}
	
	public void arcadeDrive(double throttle,double turn, boolean square){
		if(square){
			throttle = Math.pow(throttle, 2);
			turn = Math.pow(turn, 2);
		}

	    double leftMotorOutput;
	    double rightMotorOutput;
		
		double max = fabs(throttle);
		if (fabs(turn)>max){
			max = fabs(turn);
		}
		double sum = throttle + turn;
		double dif = throttle - turn;

		//Top Half
	    if(throttle>=0)
	    {
			//Righ Half
		    if(turn>=0) {
				leftMotorOutput = max;
				rightMotorOutput = dif;
			}
			//Left Half
		    else {
				leftMotorOutput = sum;
				rightMotorOutput = max;
		    }
		}
		//Bottom Half
	    else
	    {
			//Righ Half
		    if(turn>=0) {
				leftMotorOutput = sum;
				rightMotorOutput = -max;
			}
			//Left Half
		    else {
				leftMotorOutput = -max;
				rightMotorOutput = dif;
		    }
	    }
	    
	    shiftingTankDrive(leftMotorOutput,rightMotorOutput);
	}
	
	public void stopPID(){
		leftChassis.stop();
		rightChassis.stop();
	}
	
	public void startPID(){
		leftChassis.start();
		rightChassis.start();
	}
}
