package org.usfirst.frc.team2129.robot.subsystems;

import org.usfirst.frc.team2129.robot.Robot;
import org.usfirst.frc.team2129.robot.commands.ManualGearCommand;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearSubsystem extends Subsystem{
	//Inputs
	DigitalInput GearLight;
	//Outputs
	Solenoid GearSolenoid;
	
	SpeedController gearMotor;
	
	float rotateSpeed;
	
	public GearSubsystem() {
		gearMotor = Robot.map.GearMotor.get();
		GearSolenoid=new Solenoid(Robot.map.GearSolenoid);
		
		rotateSpeed = 0.5f;
	}
	
	protected void initDefaultCommand() {
		setDefaultCommand(new ManualGearCommand());
	}
	
	public void autoRotate() {
		//need ether limit switch or potentiometer to make this good
//		if (GearLight.get()) {
//			rotateLeft();
//		} else {
//			rotateStop();
//		}
	}
	
	public void rotateLeft() {
		gearMotor.set(rotateSpeed);
	}
	
	public void rotateRight() {
		gearMotor.set(rotateSpeed * -1);
	}
	
	public void rotateStop() {
		gearMotor.set(0);
	}
	
	public void open() {
		GearSolenoid.set(true);
	}

	public void close() {
		GearSolenoid.set(false);
	}
}
