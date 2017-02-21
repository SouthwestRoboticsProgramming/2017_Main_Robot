package org.usfirst.frc.team2129.robot.commands;

import edu.wpi.first.wpilibj.Timer;

public class ManualBlinkCommand extends Team2129Command {

	private Timer t;
	private Timer lt;
	private Timer rt;
	private boolean state = false;
	private boolean lstate = false;
	private boolean rstate = false;

	public ManualBlinkCommand() {
		requires(getLightsSubsystem());
		t = new Timer();
		t.start();
		lt = new Timer();
		lt.start();
		rt = new Timer();
		rt.start();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	public void execute() {
		String mode = getPreferences().getString("mode", "off");
		setSmartDashboard("mode_o", mode);

		if (mode.equals("flash")) {
			flashLights();

		} else if (mode.equals("drive")) {
			matchLightsToSpeed();

		} else if (mode.equals("continuous")) {
			turnOnBoth();

		} else {
			// Is this OFF?
			getLightsSubsystem().setLeft(getRightJoystick().getRawButton(8));
			getLightsSubsystem().setRight(getRightJoystick().getRawButton(9));
		}
	}

	private void matchLightsToSpeed() {
		double lrate = (1 - Math.abs((getLeftJoystick().getY()))) * getPreferences().getDouble("stime", 1);
		double rrate = (1 - Math.abs((getRightJoystick().getY()))) * getPreferences().getDouble("stime", 1);

		if (lt.get() > lrate) {
			lstate = !lstate;
			getLightsSubsystem().setLeft(lstate);
			lt.reset();
			lt.start();
		}

		if (rt.get() > rrate) {
			rstate = !rstate;
			getLightsSubsystem().setRight(rstate);
			rt.reset();
			rt.start();
		}
	}

	private void flashLights() {
		setSmartDashboard("timer_t", t.get());
		setSmartDashboard("time_s", state);
		double time = getPreferences().getDouble("ftime", 1);
		if (t.get() > time) {
			state = !state;
			getLightsSubsystem().setLeftOrRight(state);
			t.reset();
			t.start();
		}
	}

	private void turnOnBoth() {
		getLightsSubsystem().setLeft(true);
		getLightsSubsystem().setRight(true);
	}
}
