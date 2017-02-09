package org.usfirst.frc.team2129.robot.commands;

import org.usfirst.frc.team2129.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class UserDriveCommand extends Command {
	
	double left, right;
	
	public UserDriveCommand() {
		requires(Robot.drivetrainSubsystem);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void execute() {
		left = Robot.oi.leftJoystick.getY();
		right = Robot.oi.rightJoystick.getY();
		
		Robot.drivetrainSubsystem.tankDrive(left, right);
		Robot.drivetrainSubsystem.setShift(Robot.oi.leftJoystick.getRawButton(1)||Robot.oi.rightJoystick.getRawButton(1));
	}
}
