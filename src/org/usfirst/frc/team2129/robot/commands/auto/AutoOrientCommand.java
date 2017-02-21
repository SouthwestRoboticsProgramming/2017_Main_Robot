package org.usfirst.frc.team2129.robot.commands.auto;

import org.usfirst.frc.team2129.robot.commands.Team2129Command;

public class AutoOrientCommand extends Team2129Command {

	private double target;
	private double fuzz;
	private long done_start_time = -1;
	private long time_req;

	private boolean bostonDynamicsMode;

	public AutoOrientCommand(double target, double fuzz, double time_req_f, boolean BDM) {
		this.target = target;
		this.bostonDynamicsMode = BDM;
		this.fuzz = fuzz;
		this.time_req = 1000;// (long)(time_req*1000.);
		setSmartDashboard("AUTO_ORIENT_COMMAND", "CONSTRUCTED");
		// requires(Robot.imuSubsystem);
		requires(getDrivetrainSubsystem());
	}

	private double getAngle() {
		double a = getImuSubsystem().getZ() + target;
		while (a < -360)
			a += 360;
		if (a < 0)
			a += 360;
		while (a > 360)
			a -= 360;
		return a;
	}

	private boolean onTarget() {
		if (Math.abs(getAngle()) < fuzz)
			return true;
		if (Math.abs(getAngle() - 360) < fuzz)
			return true;
		return false;
	}

	@Override
	protected boolean isFinished() {
		if (bostonDynamicsMode)
			return false;
		if (done_start_time == -1) {
			if (onTarget()) {
				done_start_time = now();
			}
		} else {
			if (onTarget()) {
				if ((now() - done_start_time) > time_req) {
					return true;
				}
			} else {
				done_start_time = -1;
			}
		}
		return false;
	}

	private long now() {
		return System.currentTimeMillis();
	}

	public void execute() {
		setSmartDashboard("AUTO_ORIENT_COMMAND", "EXECUTING");
		double delta = getAngle();
		double dir = 1;
		if (delta > 180) {
			delta = 360 - delta;
			dir = -1;
		}
		double kP = getPreferences().getDouble("kP", 0.1);

		double calc = (delta / 360) * kP;
		calc += getPreferences().getDouble("kBASE", 0.1);

		if (calc > getPreferences().getDouble("kCAP", 0.4)) {
			calc = getPreferences().getDouble("kCAP", 0.4);
		}

		calc *= dir;

		setSmartDashboard("AOC_Angle", getAngle());
		setSmartDashboard("calc", calc);
		setSmartDashboard("ontarget", onTarget());
		setSmartDashboard("dst", done_start_time);
		setSmartDashboard("dtime", now() - done_start_time);
		setSmartDashboard("treq", time_req);

		if (!onTarget()) {
			getDrivetrainSubsystem().tankDrive(1 * calc, -1 * calc);
			setSmartDashboard("filtered_running", true);
		} else {
			getDrivetrainSubsystem().tankDrive(0, 0);
			setSmartDashboard("filtered_running", false);
		}
	}

	public void initialize() {
		setSmartDashboard("AUTO_ORIENT_COMMAND", "INITILIZING");
	}

	public void end() {
		setSmartDashboard("AUTO_ORIENT_COMMAND", "STOPPED");
	}

}
