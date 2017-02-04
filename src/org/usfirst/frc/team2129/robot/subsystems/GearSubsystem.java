package org.usfirst.frc.team2129.robot.subsystems;

import org.usfirst.frc.team2129.robot.Robot;
import org.usfirst.frc.team2129.robot.commands.GearCheckCommand;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearSubsystem extends Subsystem{
	//Inputs
	AnalogInput GearPot;
	DigitalInput GearTest;
	//Outputs
	Solenoid GearSolenoidLeft;
	Solenoid GearSolenoidRight;
	Talon GearTalon;
	
	float rotateSpeed;
	
	public GearSubsystem() {
		GearPot = new AnalogInput(Robot.map.GearPot);
		GearTest = new DigitalInput(Robot.map.GearLight);
		GearSolenoidLeft = new Solenoid(Robot.map.GearSolenoidLeft);
		GearSolenoidRight = new Solenoid(Robot.map.GearSolenoidRight);
		GearTalon = new Talon(Robot.map.GearTalon);
		
		rotateSpeed = 0.5f;
	}
	
	protected void initDefaultCommand() {
		// TODO command that calls checkForGear alot
		setDefaultCommand(new GearCheckCommand());
	}
	
	public int getPot() {
		System.out.println(GearPot.getValue());
		return GearPot.getValue();
	}
	
	public void checkForGear() {//Call often and maybe auto align gears
		if (GearTest.get()) {
			rotateRight();
		} else {
			rotateStop();
		}
	}
	
	public void rotateLeft() {
		GearTalon.set(rotateSpeed);
	}
	
	public void rotateRight() {
		GearTalon.set(rotateSpeed * -1);
	}
	
	public void rotateStop() {
		GearTalon.set(0);
	}
	
	public void open() {
		GearSolenoidLeft.set(true);
		GearSolenoidRight.set(true);
	}

	public void close() {
		GearSolenoidLeft.set(false);
		GearSolenoidRight.set(false);
	}
}
