package org.usfirst.frc.team2129.robot.map;

import java.util.SortedMap;
import java.util.TreeMap;

import org.usfirst.frc.team2129.util.CANMotorDescriptor;
import org.usfirst.frc.team2129.util.MotorDescriptor;
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
	public MotorDescriptor GearMotor = new CANMotorDescriptor(31);
	
	//Digital sensors (DI)
	public int gearLightSensor = 4;
	
	//Analog sensors (AI)
	public int gearGyro = 0;
	
	//Flashy Lights (RELAYS)
	public int FlashyLightLeft = 0;
	public int FlashyLightRight = 1;
	
	//Solenoids (SOLENOIDS)
	public int shifter = 0;
	public int gearSolenoid = 1;
	
	//Cameras
	public SortedMap<String, Integer> cameras;
	String defaultCamera = "cam0";
	public ProductionRobotMap(){
		cameras=new TreeMap<String, Integer>();
		cameras.put(defaultCamera, 0);
		cameras.put("cam1", 1);
		//cameras.put("cam2", 2);
	}
	
	//Ultrasonic
	public int Ultrasonic = 0;
}
