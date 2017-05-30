package org.usfirst.frc.team2129.robot.map;

import org.usfirst.frc.team2129.util.motordesc.MotorDescriptor;
import org.usfirst.frc.team2129.util.motordesc.VictorMotorDescriptor;

public class MechanumRobotMap {

	// Drive Motors
	public MotorDescriptor getLeftFrontMotor() {
		return new VictorMotorDescriptor(0, true);
	}

	public MotorDescriptor getLeftRearMotor() {
		return new VictorMotorDescriptor(1);
	}

	public MotorDescriptor getRightFrontMotor() {
		return new VictorMotorDescriptor(2, true);
	}

	public MotorDescriptor getRightRearMotor() {
		return new VictorMotorDescriptor(3);
	}

}
