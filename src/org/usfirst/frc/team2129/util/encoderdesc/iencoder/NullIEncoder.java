package org.usfirst.frc.team2129.util.encoderdesc.iencoder;

public class NullIEncoder extends IEncoderPIDSourceImplWrapper {

	@Override
	public double getDistance() {
		return 0;
	}

	@Override
	public double getRate() {
		return 0;
	}

	@Override
	public void zero() {
	}

}
