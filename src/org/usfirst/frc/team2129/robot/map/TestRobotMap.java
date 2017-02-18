package org.usfirst.frc.team2129.robot.map;

import java.util.SortedMap;
import java.util.TreeMap;

import org.usfirst.frc.team2129.util.encoderdesc.CANTalonEncoderDescriptor;
import org.usfirst.frc.team2129.util.encoderdesc.IEncoderDescriptor;
import org.usfirst.frc.team2129.util.encoderdesc.NullEncoderDescriptor;
import org.usfirst.frc.team2129.util.encoderdesc.QuadratureEncoderDescriptor;
import org.usfirst.frc.team2129.util.motordesc.CANMotorDescriptor;
import org.usfirst.frc.team2129.util.motordesc.MotorDescriptor;
import org.usfirst.frc.team2129.util.motordesc.NullMotorDescriptor;
import org.usfirst.frc.team2129.util.motordesc.PWMMotorDescriptor;

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
	
	public MotorDescriptor GearMotor = new CANMotorDescriptor(1);	
	
	//Encoders (DI)
	public IEncoderDescriptor leftEncoder = new QuadratureEncoderDescriptor(0, 1);
	public IEncoderDescriptor rightEncoder = new QuadratureEncoderDescriptor(2, 3);
	public IEncoderDescriptor gearEncoder = new QuadratureEncoderDescriptor(5, 6);
	public IEncoderDescriptor climbEncoder = new NullEncoderDescriptor();
	
	//Digital sensors (DI)
	public int gearLightSensor = 4;
	
	
	//Flashy Lights (RELAYS)
	public int FlashyLightLeft = 0;
	public int FlashyLightRight = 1;
	
	//Solenoids (SOLENOIDS)
	public int shifter = 0;
	public int gearSolenoid = 1;
	
	public SortedMap<String, Integer> cameras;
	String defaultCamera = "cam0";
	public TestRobotMap(){
		cameras=new TreeMap<String, Integer>();
		cameras.put(defaultCamera, 0);
	}
}
