package org.usfirst.frc.team2129.util;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;

public class SimpleShiftingGearbox implements SpeedController {
	
	double setting = 0;
	double shifterThreshold;
	double shiftDrop;
	boolean shifterInverted;
	boolean inverted=false;
	Solenoid shifter;
	SpeedController controller1;
	SpeedController controller2;
	SpeedController controller3;
	
	public SimpleShiftingGearbox(SpeedController c1, SpeedController c2, SpeedController c3,
			Solenoid solenoid, double threshold, double drop, boolean solenoidInverted){
		controller1=c1;
		controller2=c2;
		controller3=c3;
		shifterThreshold=threshold;
		shifter=solenoid;
		shiftDrop=drop;
		shifterInverted=solenoidInverted;
	}

	public void pidWrite(double output) {set(output);}
	public double get() {return setting;}
	
	public void set(double speed) {
		speed*=inverted?-1:1;
		
		setting=speed;
		
		if(Math.abs(speed)>shifterThreshold){
			speed-=(speed>0)?shiftDrop:-shiftDrop;
			speed*=(1/(1-shiftDrop));
			if(shifter!=null) shifter.set(shifterInverted);
		}else{
			if(shifter!=null) shifter.set(!shifterInverted);
		}
		
		if (controller1!=null) controller1.set(speed);
		if (controller2!=null) controller2.set(speed);
		if (controller3!=null) controller3.set(speed);
	}

	public void setInverted(boolean isInverted) {inverted=isInverted;}

	public boolean getInverted() {return inverted;}

	public void disable() {set(0);}

	public void stopMotor() {set(0);}
}
