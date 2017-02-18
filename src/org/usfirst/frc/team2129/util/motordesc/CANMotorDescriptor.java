package org.usfirst.frc.team2129.util.motordesc;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.SpeedController;

public class CANMotorDescriptor extends MotorDescriptor {
	
	private int id;
	private boolean rev = false;
	
	public CANMotorDescriptor(int canID){
		id=canID;
	}

	public CANMotorDescriptor(int i, boolean b) {
		this(i);
		rev=b;
	}

	public SpeedController _get() {
		CANTalon t = new CANTalon(id);
		t.setInverted(rev);
		return t;
	}

}
