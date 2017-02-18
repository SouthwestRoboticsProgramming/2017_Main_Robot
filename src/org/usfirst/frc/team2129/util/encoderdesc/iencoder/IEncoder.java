package org.usfirst.frc.team2129.util.encoderdesc.iencoder;

import edu.wpi.first.wpilibj.PIDSource;

public interface IEncoder extends PIDSource {
	public double getDistance();
	public double getRate();
	public void   zero();
}
