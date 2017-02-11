package org.usfirst.frc.team2129.robot.map;

import java.util.SortedMap;
import java.util.TreeMap;

import org.usfirst.frc.team2129.util.MotorDescriptor;
import org.usfirst.frc.team2129.util.NullMotorDescriptor;
import org.usfirst.frc.team2129.util.PWMMotorDescriptor;

public class TestRobotMap {

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
	
	public MotorDescriptor GearMotor = new NullMotorDescriptor();
	
	public int FlashyLightLeft = 0;
	public int FlashyLightRight = 1;
	
	
	//Potentiometers
	public int GearPot = 0;
	
	//Ultrasonic
	public int Ultrasonic = 1;

	public int GearSolenoid = 6;

	public int GearTalon=8;
	
	public int shifter = 0;
	
	public SortedMap<String, Integer> cameras;
	String defaultCamera = "cam0";
	public TestRobotMap(){
		cameras=new TreeMap<String, Integer>();
		cameras.put(defaultCamera, 0);
	}
}
