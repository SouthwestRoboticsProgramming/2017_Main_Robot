package org.usfirst.frc.team2129.robot.subsystems;

import org.usfirst.frc.team2129.robot.RobotMap;
import org.usfirst.frc.team2129.robot.commands.UserDriveCommand;
import org.usfirst.frc.team2129.util.ShiftingGearbox;
import org.usfirst.frc.team2129.util.SimpleShiftingGearbox;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Sendable;
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
	
	AnalogInput Ultrasonic1;
	
	//Mark has a plan for these
	DigitalInput LightSensorLeft;
	DigitalInput LightSensorRight;
	
	protected void initDefaultCommand() {
		setDefaultCommand(new UserDriveCommand());
	}
	
	public DrivetrainSubsystem(){
		leftGearboxMotor1   = new CANTalon(RobotMap.RightMotor1);
		leftGearboxMotor2   = new CANTalon(RobotMap.RightMotor2);
		leftGearboxMotor3   = new Jaguar(RobotMap.RightMotor3);
		leftGearboxShifter  = new DoubleSolenoid(1, 2);
		leftGearbox         = new SimpleShiftingGearbox(
			leftGearboxMotor1, leftGearboxMotor2, null,
			null, 0.5d, 0.25d, true);
		
		rightGearboxMotor1  = new CANTalon(RobotMap.LeftMotor1);
		rightGearboxMotor2  = new CANTalon(RobotMap.LeftMotor2);
		rightGearboxMotor3  = new Jaguar(RobotMap.RightMotor3);
		rightGearboxShifter = new Solenoid(0);
		rightGearbox        = new SimpleShiftingGearbox(
			rightGearboxMotor1, rightGearboxMotor2, null,
			rightGearboxShifter, 0.5d, 0.4d, false);
		
		robotDrive          = new RobotDrive(leftGearbox, rightGearbox);
		
		Ultrasonic1 = new AnalogInput(0);//needs port
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
