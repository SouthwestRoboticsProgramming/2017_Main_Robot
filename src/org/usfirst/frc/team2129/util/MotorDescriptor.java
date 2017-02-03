package org.usfirst.frc.team2129.util;

import edu.wpi.first.wpilibj.SpeedController;

public abstract class MotorDescriptor {
	private boolean got = false;
	
	public boolean getGot(){
		return got;
	}
	
	public SpeedController get(){
		if (!got){
			got=true;
			return _get();
		}
		System.out.println("TRIED TO DOUBLE-GET A MOTORDESCRIPTOR!");
		return null;
		//throw new RuntimeException("Tried to double-get a MotorDecriptror");
	}
	
	protected abstract SpeedController _get();
}
