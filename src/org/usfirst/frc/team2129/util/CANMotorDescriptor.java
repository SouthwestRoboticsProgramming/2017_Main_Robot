package org.usfirst.frc.team2129.util;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.SpeedController;

public class CANMotorDescriptor extends MotorDescriptor {
	
	private int id;
	
	public CANMotorDescriptor(int canID){
		id=canID;
	}

	public SpeedController _get() {
		return new CANTalon(id);
	}

}
