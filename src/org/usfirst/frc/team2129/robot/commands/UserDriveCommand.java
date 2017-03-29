package org.usfirst.frc.team2129.robot.commands;

import org.usfirst.frc.team2129.robot.subsystems.DrivetrainSubsystem;

public class UserDriveCommand extends Team2129Command {
	private double left, right;

	public UserDriveCommand() {
		requires(getDrivetrainSubsystem());
	}

	protected boolean isFinished() {
		return false;
	}

	protected void execute() {
		left = adjustSpeed(getLeftJoystick().getY());
		right = adjustSpeed(getRightJoystick().getY());

		getDrivetrainSubsystem().tankDrive(left, right);
	}
	
	private double adjustSpeed(double rawSpeed) {
		return getSpeedMultiplier() * rawSpeed;
	}

	private double getSpeedMultiplier() {
		return 0.5d;
		//TODO: Is SmartDashboard supposed to be wired to "Preferences" in some way?
//		return SmartDashboard.getNumber(DrivetrainSubsystem.SPEED_MULTIPLIER, 0.7d);
//		return getPreferences().getDouble(
//				(getLeftJoystick().getRawButton(1) || getRightJoystick().getRawButton(1))?DrivetrainSubsystem.SHIFT_SPEED_MULTIPLIER:DrivetrainSubsystem.SPEED_MULTIPLIER, 0.7d);
	}
}
