package org.usfirst.frc.team2129.robot.commands;

import org.usfirst.frc.team2129.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
		
		if (left > 0.75) {
			left = 0.75;
		} else if (left < -0.75) {
			left = -0.75;
		}
		
		if (right > 0.75) {
			right = 0.75;
		} else if (right < -0.75) {
			right = -0.75;
		}
		Robot.drivetrainSubsystem.tankDrive(left, right);
	}
}
