package org.usfirst.frc.team2129.robot.commands;

import org.usfirst.frc.team2129.robot.Robot;
import org.usfirst.frc.team2129.robot.subsystems.DrivetrainSubsystem;

public class MechanumDriveCommand extends Team2129Command {

	double rightMulti;
	double multi;
	double notFull;

	public MechanumDriveCommand() {
		requires(Robot.mechanumDrivetrainSubsystem);
//		requires(getDrivetrainSubsystem());
		notFull = 0.7;
		rightMulti = getPreferences().getDouble("RightMulti", 0.75);
	}

	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void execute() {
		rightMulti = 1.0;// getPreferences().getDouble("rightMulti", 0.75);
		double left = adjustSpeed(getLeftJoystick().getY());
		double right = adjustSpeed(getRightJoystick().getY());
		//
		// if (isShifted()) {
		// getDrivetrainSubsystem().setShift(true);
		// multi = 1;
		// } else {
		// getDrivetrainSubsystem().setShift(false);
		// multi = notFull;
		// }

//		getDrivetrainSubsystem().setShift(false);
//		if (isSlowForwardMode()) {
//			getDrivetrainSubsystem().tankDrive(-0.5 * rightMulti, -0.5);
//
//		} else if (isFastForwardMode()) {
//			getDrivetrainSubsystem().setShift(true);
//			getDrivetrainSubsystem().tankDrive(-1.0 * rightMulti, -1.0);
//
//		} else {
//			if (Robot.USE_ARCADE_DRIVE) {
//				getDrivetrainSubsystem().arcadeDrive(-getLeftJoystick().getY(), -getLeftJoystick().getTwist());
//			} else {
//				getDrivetrainSubsystem().tankDrive(left * rightMulti, right);
//			}
//		}
	}

	private boolean isFastForwardMode() {
		return getLeftJoystick().getRawButton(SHIFTER_BUTTON);
	}

	private boolean isSlowForwardMode() {
		return getRightJoystick().getRawButton(SLOW_DRIVE_BUTTON);
	}

	private double adjustSpeed(double rawSpeed) {
		return rawSpeed;// getSpeedMultiplier() * rawSpeed;
	}

	private double getSpeedMultiplier() {
		// return 0.7d;
		// TODO: Is SmartDashboard supposed to be wired to "Preferences" in some
		// way?
		// return SmartDashboard.getNumber(DrivetrainSubsystem.SPEED_MULTIPLIER,
		// 0.7d);
		return getPreferences().getDouble(
				(getLeftJoystick().getRawButton(SHIFTER_BUTTON)) ? DrivetrainSubsystem.SHIFT_SPEED_MULTIPLIER : DrivetrainSubsystem.SPEED_MULTIPLIER, 0.7d);
	}
}
