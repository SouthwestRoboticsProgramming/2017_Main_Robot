package org.usfirst.frc.team2129.robot.commands.auto;

import org.usfirst.frc.team2129.robot.commands.Team2129Command;

public class CalibrateGearGyroCommand extends Team2129Command {
	boolean finished = false;
	boolean started = false;

	public CalibrateGearGyroCommand() {
		requires(getGearSubsystem());
	}

	public void initialize() {
		finished = false;
		started = false;
	}

	protected boolean isFinished() {
		return finished;
	}

	public void execute() {
		double rate = getPreferences().getDouble("gear_0_rate", -0.3);
		if (started) {
			if (Math.abs(getGearSubsystem().getRate()) < 1 || finished) {
				// UGH. Just have the GearSubsystem do this .. drilling drilling
				getGearSubsystem().zeroAngle();
				getGearMotor().set(0);
				finished = true;
				setSmartDashboard("gc_state", "1_done");
			} else {
				getGearMotor().set(rate);
			}
		} else {
			if (Math.abs(getGearSubsystem().getRate()) > 0) {
				started = true;
			}
			getGearMotor().set(rate);
		}
	}
}