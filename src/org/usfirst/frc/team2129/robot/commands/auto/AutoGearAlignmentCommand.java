package org.usfirst.frc.team2129.robot.commands.auto;

import org.usfirst.frc.team2129.robot.commands.Team2129Command;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoGearAlignmentCommand extends Team2129Command {

	private String offsetKey = "L_OFF_X";

	public AutoGearAlignmentCommand() {
		requires(getDrivetrainSubsystem());
	}

	protected int getOffset() {
		return (int) SmartDashboard.getNumber(offsetKey, 0);
	}

	protected boolean isFinished() {
		return false;
	}

	public void execute() {
		int offset = getOffset();
		boolean side = offset > 0;
		double speed = ((double) Math.abs(offset) / 90.) * getPreferences().getDouble("gear_align_spd", 0.3);
		speed += getPreferences().getDouble("gear_align_base", 0.3);

		if (Math.max(Math.abs(getDrivetrainSubsystem().getLeftEncoder().getRate()),
				Math.abs(getDrivetrainSubsystem().getRightEncoder().getRate())) < getPreferences().getDouble("gear_align_0fuzz", 4)) {
			speed += getPreferences().getDouble("gear_align_0bonus", 0.1);
		}

		speed *= getPreferences().getDouble("gear_align_emul", -1);
		setSmartDashboard("calc", speed);
		setSmartDashboard("aga_side", side);

		if (Math.abs(getOffset()) < getPreferences().getDouble("gear_align_fuzz", 2)) {
			double sts = getPreferences().getDouble("gear_align_sts", -0.5);
			setSmartDashboard("sts", true);
			getDrivetrainSubsystem().tankDrive(sts, sts);
		} else {
			setSmartDashboard("sts", false);
			double opp_spd = getPreferences().getDouble("gear_align_opp", 0.2);
			getDrivetrainSubsystem().tankDrive(side ? speed : opp_spd, side ? opp_spd : speed);
		}
	}
}
