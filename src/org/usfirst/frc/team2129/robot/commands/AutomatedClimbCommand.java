package org.usfirst.frc.team2129.robot.commands;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Timer;

public class AutomatedClimbCommand extends ClimbCommand {

	private static final double AUTO_CLIMB_DONE_TIME = 0.25;
	private static final double AUTO_CLIMB_SPEED = 0.6;
	private Timer timer;
	private boolean finished;
	private boolean started;
	private String climb_speed_var;

	public AutomatedClimbCommand(String climb_speed_var) {
		super();
		requires(getSubsystem());
		timer = new Timer();
		timer.reset();
		timer.stop();
		this.climb_speed_var=climb_speed_var;
	}

	@Override
	public void initialize() {
		finished = false;
		started = false;
	}

	@Override
	public void execute() {
		setSmartDashboard("auto_climb_time", timer.get());

		if (getClimberRate() < getClimbingThreshold()) {
			if (!started) {
				timer.reset();
				timer.start();
				started = true;
			}
		} else {
			started = false;
			timer.reset();
			timer.stop();
		}

		if (timer.get() > Preferences.getInstance().getDouble("auto_climb_done_time", AUTO_CLIMB_DONE_TIME)) {
			finish();
		} else {
			// This is really pointless if we're already climbing??
			climb();
		}
	}

	private void climb() {
		getSubsystem().setSpeed(Preferences.getInstance().getDouble(climb_speed_var, AUTO_CLIMB_SPEED));
	}

	private double getClimbingThreshold() {
		return Preferences.getInstance().getDouble("auto_climb_off_threshold", 1);
	}
	
	private double getClimberRate() {
		return Math.abs(getSubsystem().encoder.getRate());
	}

	@Override
	public void end() {
		stopClimbing();
	}

	@Override
	protected boolean isFinished() {
		return finished;
	}

	public void finish() {
		stopClimbing();
		this.finished = true;
	}

}
