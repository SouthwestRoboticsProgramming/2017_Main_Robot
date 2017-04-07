package org.usfirst.frc.team2129.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoAlignAndDriveCommand extends CommandGroup {
	public AutoAlignAndDriveCommand(String speedPreferenceArg, String timePreferenceArg) {
		if (SmartDashboard.getBoolean("autoAlign", false)) {
			addSequential(new AutoGearAlignmentCommand());
		}
		addSequential(new DriveForward(speedPreferenceArg, timePreferenceArg));
	}
}
