package org.usfirst.frc.team2129.robot.map;

import org.usfirst.frc.team2129.util.MotorDescriptor;
import org.usfirst.frc.team2129.util.NullMotorDescriptor;
import org.usfirst.frc.team2129.util.PWMMotorDescriptor;

public class TestRobotMap {
	public int GearLimitLeft = 4;

	//Compressor
	public int Compressor = 0;
	
	//Drive Motors
	public MotorDescriptor LeftMotor1 = new PWMMotorDescriptor(0);
	public MotorDescriptor LeftMotor2 = new PWMMotorDescriptor(1);
	public MotorDescriptor RightMotor1 = new PWMMotorDescriptor(2, true);
	public MotorDescriptor RightMotor2 = new PWMMotorDescriptor(3, true);
	
	//Lifter Motors
	public MotorDescriptor LiftMotor1 = new NullMotorDescriptor();
	public MotorDescriptor LiftMotor2 = new NullMotorDescriptor();
	
	//Light Sensors
	public int GearLight = 0;
	public int DriveLightLeft = 1;
	public int DriveLightRight = 2;
	
	//Solenoids
	public int ShifterRight = 0;
	public int ShiftLeft1 = 1;
	public int ShifterLeft2 = 2;
	public int GearSolenoid1 = 3;
	public int GearSolenoid2 = 4;
	
	//Ultrasonic
	public int Ultrasonic = 0;

	public int GearLimitRight = 5;

	public int GearSolenoidLeft = 6;

	public int GearSolenoidRight=7;

	public int GearTalon=8;
}
