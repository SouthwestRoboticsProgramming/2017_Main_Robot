package org.usfirst.frc.team2129.robot.subsystems;

import org.usfirst.frc.team2129.robot.Robot;
import org.usfirst.frc.team2129.robot.commands.ManualGearCommand;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearSubsystem extends Subsystem{
	//Inputs
	AnalogInput GearPot;
	DigitalInput GearTest;
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
		// TODO command that calls checkForGear alot
		setDefaultCommand(new ManualGearCommand());
	}
	
	public int getPot() {
		//System.out.println(GearPot.getValue());
		return 0;//GearPot.getValue();
	}
	
	public void checkForGear() {//Call often and maybe auto align gears
//		if (GearTest.get()) {
//			rotateRight();
//		} else {
//			rotateStop();
//		}
	}
	
	public void rotateLeft() {
//		gearMotor.set(rotateSpeed);
	}
	
	public void rotateRight() {
//		gearMotor.set(rotateSpeed * -1);
	}
	
	public void rotateStop() {
//		gearMotor.set(0);
	}
	
	public void open() {
		GearSolenoid.set(true);
	}

	public void close() {
		GearSolenoid.set(false);
	}
}
