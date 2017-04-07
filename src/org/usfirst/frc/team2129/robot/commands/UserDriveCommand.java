package org.usfirst.frc.team2129.robot.commands;

import org.usfirst.frc.team2129.robot.subsystems.DrivetrainSubsystem;

public class UserDriveCommand extends Team2129Command {

	public UserDriveCommand() {
		requires(getDrivetrainSubsystem());
	}

	protected boolean isFinished() {
		return false;
	}

	protected void execute() {
		double left = adjustSpeed(getLeftJoystick().getY());
		double right = adjustSpeed(getRightJoystick().getY());

		// Go forward slowly Fix
		if (getRightJoystick().getRawButton(2)) {
			getDrivetrainSubsystem().tankDrive(-0.5, -0.5);
		} else {
			getDrivetrainSubsystem().tankDrive(left, right);
		}
	}

	private double adjustSpeed(double rawSpeed) {
		return getSpeedMultiplier() * rawSpeed;
	}

	private double getSpeedMultiplier() {
		// return 0.7d;
		// TODO: Is SmartDashboard supposed to be wired to "Preferences" in some
		// way?
		// return SmartDashboard.getNumber(DrivetrainSubsystem.SPEED_MULTIPLIER,
		// 0.7d);
		return getPreferences().getDouble((getLeftJoystick().getRawButton(SHIFTER_BUTTON)) ? DrivetrainSubsystem.SHIFT_SPEED_MULTIPLIER
				: DrivetrainSubsystem.SPEED_MULTIPLIER, 0.7d);
	}
}
