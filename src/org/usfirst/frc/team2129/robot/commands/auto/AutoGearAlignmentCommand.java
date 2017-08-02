package org.usfirst.frc.team2129.robot.commands.auto;

import org.usfirst.frc.team2129.robot.Robot;
import org.usfirst.frc.team2129.robot.commands.Team2129Command;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoGearAlignmentCommand extends Team2129Command {

	public static final String OFFSET_OK = "L_OFF_X_OK";
	private String offsetKey = "L_OFF_X";
	boolean isFinished;

	public AutoGearAlignmentCommand() {
		requires(getDrivetrainSubsystem());
		isFinished = false;
	}

	protected int getOffset() {
		return (int) SmartDashboard.getNumber(offsetKey, 0);
	}

	protected boolean isFinished() {
		return !SmartDashboard.getString(OFFSET_OK, "NO").equals("OK");
	}

	public void execute() {
		System.err.println("Running");
		SmartDashboard.putNumber("AutoGearAlignmentCommand_time", System.currentTimeMillis());
		SmartDashboard.putString("loffxok_v_v", SmartDashboard.getString(OFFSET_OK, "NO"));
		if (!SmartDashboard.getString(OFFSET_OK, "NO").equals("OK")){
//			Robot.drivetrainSubsystem.tankDrive(0, 0);
			return;
		}
		int offset = getOffset();
		
		boolean side = offset > 0;
		side = Preferences.getInstance().getBoolean("gear_align_inv_side", false)?!side:side;
		double speed = ((double) Math.abs(offset) / 90.) * getPreferences().getDouble("gear_align_spd", 0.3);
		speed += getPreferences().getDouble("gear_align_base", 0.3);

		if (Math.max(Math.abs(getDrivetrainSubsystem().getLeftIEncoder().getRate()),
				Math.abs(getDrivetrainSubsystem().getRightIEncoder().getRate())) < getPreferences().getDouble("gear_align_0fuzz", 4)) {
			speed += getPreferences().getDouble("gear_align_0bonus", 0.1);
		}

		speed *= getPreferences().getDouble("gear_align_emul", -1);
		setSmartDashboard("calc", speed);
		setSmartDashboard("aga_side", side);
		double inv = (getPreferences().getBoolean("gear_align_inv", false)?-1.:1.);

		if (Math.abs(getOffset()) < getPreferences().getDouble("gear_align_fuzz", 2)) {
			double sts = getPreferences().getDouble("gear_align_sts", -0.5);
			setSmartDashboard("sts", true);
			getDrivetrainSubsystem().tankDrive(inv*sts, inv*sts);
		} else {
			setSmartDashboard("sts", false);
			double opp_spd = getPreferences().getDouble("gear_align_opp", 0.2);
			getDrivetrainSubsystem().tankDrive(side ? inv*speed : inv*opp_spd, side ? inv*opp_spd : inv*speed);
		}
	}
}
