package org.usfirst.frc.team2129.robot.commands.auto;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoAlignAndDriveCommand extends CommandGroup {
	private static final double DEFAULT_SPEED = -0.5;
	private static final double DEFAULT_TIME_IN_SECONDS = 2;

	// default .5 speed
	private double getSpeed(String preferenceArg) {
		return getPreferences().getDouble(preferenceArg, DEFAULT_SPEED);
	}

	// default 2 seconds
	private double getTime(String preferenceArg) {
		return getPreferences().getDouble(preferenceArg, DEFAULT_TIME_IN_SECONDS);
	}
	
	public AutoAlignAndDriveCommand(String speedPreferenceArg, String timePreferenceArg) {
		if (SmartDashboard.getBoolean("autoAlign", false)) {
			addSequential(new AutoGearAlignmentCommand());
		}
		addSequential(new DriveForward(getSpeed(speedPreferenceArg), getTime(timePreferenceArg)));
	}

	private Preferences getPreferences() {
		return Preferences.getInstance();
	}
}
