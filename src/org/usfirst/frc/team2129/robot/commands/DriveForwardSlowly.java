package org.usfirst.frc.team2129.robot.commands;

public class DriveForwardSlowly extends Team2129Command {
	private static final double leftSpeed = 0.6;
	private static final double rightSpeed = 0.6;

	public DriveForwardSlowly() {
	}

	protected void execute() {
		getDrivetrainSubsystem().tankDrive(leftSpeed, rightSpeed);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
}
