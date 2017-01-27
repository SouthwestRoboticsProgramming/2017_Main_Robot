package org.usfirst.frc.team2129.robot.subsystems;

import org.usfirst.frc.team2129.robot.RobotMap;
import org.usfirst.frc.team2129.robot.commands.LifterStop;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LifterSubsystem extends Subsystem {

	Talon LifterTalon;
	
	float upSpeed;
	float downSpeed;
	
	protected void initDefaultCommand() {
		setDefaultCommand(new LifterStop());
	}
	
	public LifterSubsystem() {
		setDefaultCommand(new LifterStop());
		LifterTalon = new Talon(RobotMap.LiftMotor);
	}
	
	public void GoUp() {
		LifterTalon.set(upSpeed);
	}
	
	public void GoDown() {
		LifterTalon.set(downSpeed);
	}
	
	public void Stop() {
		LifterTalon.set(0.0);
	}

}
