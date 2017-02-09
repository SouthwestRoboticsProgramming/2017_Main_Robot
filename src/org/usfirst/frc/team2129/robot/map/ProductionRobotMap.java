package org.usfirst.frc.team2129.robot.map;

import java.util.SortedMap;
import java.util.TreeMap;

import org.usfirst.frc.team2129.util.CANMotorDescriptor;
import org.usfirst.frc.team2129.util.MotorDescriptor;
import org.usfirst.frc.team2129.util.NullMotorDescriptor;
import org.usfirst.frc.team2129.util.PWMMotorDescriptor;

public class ProductionRobotMap {
	//Compressor
	public int Compressor = 0;
	
	//Drive Motors
	public MotorDescriptor LeftMotor1 = new CANMotorDescriptor(10);
	public MotorDescriptor LeftMotor2 = new CANMotorDescriptor(11);
	public MotorDescriptor RightMotor1 = new CANMotorDescriptor(20);
	public MotorDescriptor RightMotor2 = new CANMotorDescriptor(21);
	
	//Lifter Motors
	public MotorDescriptor LiftMotor1 = new PWMMotorDescriptor(0);
	public MotorDescriptor LiftMotor2 = new PWMMotorDescriptor(1);
	
	//Gear Stuff
	public MotorDescriptor GearMotor = new NullMotorDescriptor();// new PWMMotorDescriptor(31);
	
	//Light Sensors
	public int GearLight = 5;
	public int DriveLightLeft = 6;
	public int DriveLightRight = 7;
	
	//Flashy Lights
	public int FlashyLightLeft = 0;
	public int FlashyLightRight = 1;
	
	//Solenoids
	public int shifter = 0;
	public int GearSolenoid = 1;
	
	//Cameras
	public SortedMap<String, Integer> cameras;
	public ProductionRobotMap(){
		cameras=new TreeMap<String, Integer>();
		cameras.put("First Camera", 0);
		cameras.put("Second Camera", 1);
		cameras.put("Third Camera", 2);
	}
	
	//Ultrasonic
	public int Ultrasonic = 0;
}
