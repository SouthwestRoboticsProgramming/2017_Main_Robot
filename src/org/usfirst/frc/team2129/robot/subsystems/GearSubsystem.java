package org.usfirst.frc.team2129.robot.subsystems;

import org.usfirst.frc.team2129.robot.Robot;
import org.usfirst.frc.team2129.robot.commands.GearCheckCommand;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearSubsystem extends Subsystem{
	//Inputs
	DigitalInput GearTest;
	//Outputs
	DoubleSolenoid GearSolenoid;
	Talon GearTalon;
	
	float rotateSpeed;
	
	public GearSubsystem() {
		GearTest = new DigitalInput(Robot.map.GearLight);//get port #s for all of these
		GearSolenoid = null;//new DoubleSolenoid(RobotMap.GearSolenoid1 ,RobotMap.GearSolenoid2);//make these actual ports
		GearTalon = null;//new Talon(RobotMap.GearTalon);
		
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
		GearSolenoid.set(DoubleSolenoid.Value.kForward);
	}

	public void close() {
		GearSolenoid.set(DoubleSolenoid.Value.kReverse);
	}
}
