package org.usfirst.frc.team2129.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;

public class SplitSpeedController implements SpeedController {
	private double state;
	private boolean inv;
	private SpeedController s1, s2;

	public SplitSpeedController(SpeedController s1, SpeedController s2) {
		this.s1 = s1;
		this.s2 = s2;
	}

	public void pidWrite(double output) {
		set(output);
	}

	public double get() {
		return state;
	}

	public void set(double speed) {
		state = speed;
		speed *= inv ? -1 : 1;
		s1.set(speed);
		s2.set(speed);
	}

	public void setInverted(boolean isInverted) {
		inv = isInverted;
	}

	public boolean getInverted() {
		return inv;
	}

	public void disable() {
		set(0);
	}

	public void stopMotor() {
		disable();
	}
}
