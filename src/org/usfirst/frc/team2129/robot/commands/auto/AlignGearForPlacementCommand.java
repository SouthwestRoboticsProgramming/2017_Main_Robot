package org.usfirst.frc.team2129.robot.commands.auto;

import org.usfirst.frc.team2129.robot.commands.Team2129Command;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;

public class AlignGearForPlacementCommand extends Team2129Command {
	private PIDController gearPID;
	private Timer t;
	private double target;

	public AlignGearForPlacementCommand() {
		requires(getGearSubsystem());
		gearPID = new PIDController(0.1, 0.01, 0, getGearSubsystem().getPIDSource(), getGearMotor());
		t = new Timer();
	}

	protected boolean isFinished() {
		return false;
	}

	public void initialize() {
		target = getPreferences().getDouble("gear_recv_pos", 45);
		gearPID.setSetpoint(target);
		gearPID.setAbsoluteTolerance(getPreferences().getDouble("gear_recv_tol", 3));
		gearPID.setPID(getPreferences().getDouble("gear_recv_p", 0.1), getPreferences().getDouble("gear_recv_i", 0.01),
				getPreferences().getDouble("gear_recv_d", 0));
		gearPID.setOutputRange(-0.5, 0.5);
		gearPID.enable();
		t.reset();
		t.start();
	}

	public void execute() {
		setSmartDashboard("gear_recv_out", gearPID.get());
		setSmartDashboard("gear_recv_delta", gearPID.getDeltaSetpoint());
		setSmartDashboard("gear_recv_ps_rd", getGearSubsystem().getPIDSource().pidGet());
		setSmartDashboard("gear_ali_target", target);

		if (!getGearSubsystem().getGearGood() && gearPID.onTarget()) {
			if (t.get() > getPreferences().getDouble("gear_rot_time", 0.1)) {
				t.reset();
				t.start();
				target += getPreferences().getDouble("gear_rot_delta", 3);
				gearPID.setSetpoint(target);
			}
		}
	}

	public void end() {
		gearPID.disable();
	}
}
