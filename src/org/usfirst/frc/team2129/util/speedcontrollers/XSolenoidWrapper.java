package org.usfirst.frc.team2129.util.speedcontrollers;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;

public class XSolenoidWrapper {
	private Solenoid singleSolenoid;
	private DoubleSolenoid doubleSolenoid;

	public XSolenoidWrapper(Solenoid s) {
		this.singleSolenoid = s;
	}

	public XSolenoidWrapper(DoubleSolenoid d) {
		this.doubleSolenoid = d;
	}

	public void set(boolean state) {
		if (singleSolenoid != null)
			singleSolenoid.set(state);
		if (doubleSolenoid != null)
			doubleSolenoid.set(state ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
	}
}
