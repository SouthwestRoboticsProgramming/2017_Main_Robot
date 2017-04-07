package org.usfirst.frc.team2129.robot.commands.auto;

import org.usfirst.frc.team2129.robot.commands.Team2129Command;

import edu.wpi.first.wpilibj.Timer;

public class DriveForward extends Team2129Command {
	private static final double DEFAULT_SPEED = -0.5;
	private static final double DEFAULT_TIME_IN_SECONDS = 2;
	private Timer timer;
	private String speedPreferenceArg;
	private String timePreferenceArg;

	// default .5 speed
	private double getSpeed() {
		return getPreferences().getDouble(speedPreferenceArg, DEFAULT_SPEED);
	}

	// default 2 seconds
	private double getTime() {
		return getPreferences().getDouble(timePreferenceArg, DEFAULT_TIME_IN_SECONDS);
	}

	public DriveForward(String speedPreferenceArg, String timePreferenceArg) {
		this.speedPreferenceArg = speedPreferenceArg;
		this.timePreferenceArg = timePreferenceArg;
		timer = new Timer();
	}

	public void initialize() {
		timer.reset();
		timer.start();
	}

	public void execute() {
		getDrivetrainSubsystem().tankDrive(getSpeed(), getSpeed());
	}

	@Override
	protected boolean isFinished() {
		return timer.get() > getTime();
	}
}
