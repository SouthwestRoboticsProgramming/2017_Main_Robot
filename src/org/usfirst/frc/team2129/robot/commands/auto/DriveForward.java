package org.usfirst.frc.team2129.robot.commands.auto;

import org.usfirst.frc.team2129.robot.commands.Team2129Command;

import edu.wpi.first.wpilibj.Timer;

public class DriveForward extends Team2129Command {
	private Timer timer;
	private double speed;
	private double time;

	public DriveForward(double speed, double time) {
		super();
		this.speed = speed;
		this.time = time;
		timer = new Timer();
	}

	public void initialize() {
		timer.reset();
		timer.start();
	}

	public void execute() {
//		getDrivetrainSubsystem().tankDrive(speed, speed);
	}

	@Override
	protected boolean isFinished() {
		return timer.get() > time;
	}
}
