package org.usfirst.frc.team2129.robot.map;

import java.util.SortedMap;
import java.util.TreeMap;

import org.usfirst.frc.team2129.util.encoderdesc.CANTalonEncoderDescriptor;
import org.usfirst.frc.team2129.util.encoderdesc.IEncoderDescriptor;
import org.usfirst.frc.team2129.util.encoderdesc.QuadratureEncoderDescriptor;
import org.usfirst.frc.team2129.util.motordesc.CANMotorDescriptor;
import org.usfirst.frc.team2129.util.motordesc.MotorDescriptor;
import org.usfirst.frc.team2129.util.motordesc.PWMMotorDescriptor;

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
	
	//Encoders (DI)
	public IEncoderDescriptor leftEncoder = new QuadratureEncoderDescriptor(0, 1);
	public IEncoderDescriptor rightEncoder = new QuadratureEncoderDescriptor(2, 3);
	public IEncoderDescriptor gearEncoder = new QuadratureEncoderDescriptor(5, 6);
	public IEncoderDescriptor climbEncoder = new CANTalonEncoderDescriptor(RightMotor2);
	
	//Digital sensors (DI)
	public int gearLightSensor = 4;
	
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
