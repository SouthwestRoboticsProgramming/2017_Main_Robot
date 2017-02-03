package org.usfirst.frc.team2129.util;

import edu.wpi.first.wpilibj.SpeedController;

public class NullMotorDescriptor extends MotorDescriptor {

	@Override
	protected SpeedController _get() {
		return new NullSpeedController();
	}

}
