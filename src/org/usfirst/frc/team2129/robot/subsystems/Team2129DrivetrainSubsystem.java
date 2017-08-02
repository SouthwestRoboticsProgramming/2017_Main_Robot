package org.usfirst.frc.team2129.robot.subsystems;

import org.usfirst.frc.team2129.util.encoderdesc.iencoder.IEncoder;

public abstract class Team2129DrivetrainSubsystem extends Team2129Subsystem {

	public void tankDrive(double left, double right) {
		// no op
	}

	public void arcadeDrive(double moveValue, double rotateValue) {
		// no op
	}

	public void setReversed(boolean state) {
		// no op
	}

	public void setShift(boolean state) {
		// no op
	}

	public IEncoder getLeftIEncoder() {
		return null;
	}

	public IEncoder getRightIEncoder() {
		return null;
	}

	public void setDashboardValues() {
		// no op
	}
}