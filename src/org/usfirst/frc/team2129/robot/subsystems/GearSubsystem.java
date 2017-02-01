package org.usfirst.frc.team2129.robot.subsystems;

import org.usfirst.frc.team2129.robot.RobotMap;
import org.usfirst.frc.team2129.robot.commands.GearCheckCommand;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearSubsystem extends Subsystem{
	//Inputs
	DigitalInput GearTest;
	//Outputs
	Solenoid GearSolenoidLeft;
	Solenoid GearSolenoidRight;
	Talon GearTalon;
	
	float rotateSpeed;
	
	public GearSubsystem() {
		GearTest = new DigitalInput(RobotMap.GearLight);
		GearSolenoidLeft = new Solenoid(RobotMap.GearSolenoidLeft);
		GearSolenoidRight = new Solenoid(RobotMap.GearSolenoidRight);
		GearTalon = new Talon(RobotMap.GearTalon);
		
		rotateSpeed = 0.5f;
	}
	
	protected void initDefaultCommand() {
		// TODO command that calls checkForGear alot
		setDefaultCommand(new GearCheckCommand());
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
