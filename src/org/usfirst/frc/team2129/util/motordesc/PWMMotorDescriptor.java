package org.usfirst.frc.team2129.util.motordesc;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.SpeedController;

public class PWMMotorDescriptor extends MotorDescriptor {
	private int id;
	private boolean invert = false;

	public PWMMotorDescriptor(int pwmID) {
		id = pwmID;
	}

	public PWMMotorDescriptor(int pwmID, boolean invert) {
		id = pwmID;
		this.invert = invert;
	}

	public SpeedController _get() {
		Jaguar j = new Jaguar(id);
		j.setInverted(invert);
		return j;
	}

}
