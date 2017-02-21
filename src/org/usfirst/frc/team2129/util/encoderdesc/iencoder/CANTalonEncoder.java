package org.usfirst.frc.team2129.util.encoderdesc.iencoder;

import com.ctre.CANTalon;

public class CANTalonEncoder extends IEncoderPIDSourceImplWrapper {
	private CANTalon _talon;

	public CANTalonEncoder(CANTalon talon) {
		_talon = talon;
	}

	public double getDistance() {
		return _talon.getPosition();
	}

	public double getRate() {
		return _talon.getSpeed();
	}

	public void zero() {
		_talon.reset();
	}
}
