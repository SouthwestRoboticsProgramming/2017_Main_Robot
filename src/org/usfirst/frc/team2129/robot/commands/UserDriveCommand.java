package org.usfirst.frc.team2129.robot.commands;

import org.usfirst.frc.team2129.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class UserDriveCommand extends Command {
	
	public UserDriveCommand() {
		requires(Robot.drivetrainSubsystem);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void execute() {
		Robot.drivetrainSubsystem.tankDrive(Robot.oi.leftJoystick.getY(), Robot.oi.rightJoystick.getY());
		SmartDashboard.putNumber("encoder_traversal", Robot.drivetrainSubsystem.leftEncoder.getDistance());
		SmartDashboard.putNumber("encoder_speed", Robot.drivetrainSubsystem.leftEncoder.getRate());
	}
}
