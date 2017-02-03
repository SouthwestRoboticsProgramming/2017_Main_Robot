package org.usfirst.frc.team2129.robot.subsystems;

import org.usfirst.frc.team2129.robot.Robot;
import org.usfirst.frc.team2129.robot.commands.UserDriveCommand;
import org.usfirst.frc.team2129.util.SimpleShiftingGearbox;
import org.usfirst.frc.team2129.util.XSolenoidWrapper;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DrivetrainSubsystem extends Subsystem {
	public SpeedController leftGearboxMotor1;
	public SpeedController leftGearboxMotor2;
	public SpeedController leftGearboxMotor3;
	public DoubleSolenoid        leftGearboxShifter;
	public SimpleShiftingGearbox leftGearbox;
	
	public SpeedController rightGearboxMotor1;
	public SpeedController rightGearboxMotor2;
	public SpeedController rightGearboxMotor3;
	public Solenoid  rightGearboxShifter;
	public SimpleShiftingGearbox rightGearbox;
	
	public RobotDrive      robotDrive;
	

	public Encoder leftEncoder;
	public Encoder rightEncoder;

	AnalogInput Ultrasonic1;
	
	//Mark has a plan for these
	DigitalInput LightSensorLeft;
	DigitalInput LightSensorRight;

	protected void initDefaultCommand() {
		setDefaultCommand(new UserDriveCommand());
	}
	
	public DrivetrainSubsystem(){
		leftGearboxMotor1   = Robot.map.RightMotor1.get();
		leftGearboxMotor2   = Robot.map.RightMotor2.get();
		leftGearboxMotor3   = null;
		leftGearboxShifter  = new DoubleSolenoid(Robot.map.ShiftLeft1, Robot.map.ShifterLeft2);
		leftGearbox         = new SimpleShiftingGearbox(
			leftGearboxMotor1, leftGearboxMotor2, leftGearboxMotor3,
			null, 0.6d, 0.25d, true);
		
		rightGearboxMotor1  = Robot.map.LeftMotor1.get();
		rightGearboxMotor2  = Robot.map.LeftMotor2.get();
		rightGearboxMotor3  = null;
		rightGearboxShifter = new Solenoid(Robot.map.ShifterRight);
		rightGearbox        = new SimpleShiftingGearbox(
			rightGearboxMotor1, rightGearboxMotor2, rightGearboxMotor3,
			new XSolenoidWrapper(rightGearboxShifter), 0.6d, 0.4d, false);
		
		robotDrive          = new RobotDrive(leftGearbox, rightGearbox);
		
		leftEncoder = new Encoder(0, 1);
		rightEncoder = new Encoder(2, 3);
		//Ultrasonic1 = new AnalogInput(RobotMap.Ultrasonic);//needs port
		//LightSensorLeft = new DigitalInput(RobotMap.DriveLightLeft);
		//LightSensorRight = new DigitalInput(RobotMap.DriveLightRight);
	}
	
	public void tankDrive(double left, double right){
		robotDrive.tankDrive(left, right);
	}
	//Mark has a plan for all of this...
	public int getValue() {
		return Ultrasonic1.getValue();
	}
	
	public double getVoltage() {
		return Ultrasonic1.getVoltage();
	}
	
	public boolean getLeft() {
		return LightSensorLeft.get();
	}
	
	public boolean getRight() {
		return LightSensorRight.get();
	}
}
