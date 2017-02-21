package org.usfirst.frc.team2129.robot.subsystems;

import org.usfirst.frc.team2129.robot.Robot;
import org.usfirst.frc.team2129.robot.commands.Team2129GlobalInterface;
import org.usfirst.frc.team2129.util.encoderdesc.iencoder.IEncoder;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class Team2129Subsystem extends Subsystem implements Team2129GlobalInterface {

	//  Motors
	
	protected SpeedController getLiftMotor1() {
		return Robot.map.LiftMotor1.get();
	}

	protected SpeedController getLiftMotor2() {
		return Robot.map.LiftMotor2.get();
	}

	protected SpeedController getLeftMotor1() {
		return Robot.map.LeftMotor1.get();
	}

	protected SpeedController getLeftMotor2() {
		return Robot.map.LeftMotor2.get();
	}

	protected SpeedController getRightMotor1() {
		return Robot.map.RightMotor1.get();
	}

	protected SpeedController getRightMotor2() {
		return Robot.map.RightMotor2.get();
	}
	
	protected int getShifter() {
		return Robot.map.shifter;
	}
	
	protected int getLeftFlashyLight() {
		return Robot.map.FlashyLightLeft;
	}
	
	protected int getRightFlashyLight() {
		return Robot.map.FlashyLightRight;
	}
	
	// Encoders

	protected IEncoder getClimbEncoder() {
		return Robot.map.climbEncoder.get();
	}

	protected IEncoder getLeftEncoder() {
		return Robot.map.leftEncoder.get();
	}

	protected IEncoder getRightEncoder() {
		return Robot.map.rightEncoder.get();
	}
	
	protected void freezeImu() {
		getImuSubsystem().freeze();
	}
	
	protected void unFreezeImu() {
		getImuSubsystem().freeze();
	}
}
