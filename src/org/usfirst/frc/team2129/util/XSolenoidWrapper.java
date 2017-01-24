package org.usfirst.frc.team2129.util;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;

public class XSolenoidWrapper {
	Solenoid s;
	DoubleSolenoid d;
	
	public XSolenoidWrapper(Solenoid s){
		this.s=s;
	}
	
	public XSolenoidWrapper(DoubleSolenoid d){
		this.d=d;
	}
	
	public void set(boolean state){
		if(this.s!=null) this.s.set(state);
		if(this.d!=null) this.d.set(state?DoubleSolenoid.Value.kForward:DoubleSolenoid.Value.kReverse);
	}
}
