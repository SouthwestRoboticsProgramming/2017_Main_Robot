package org.usfirst.frc.team2129.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoAlignAndDriveCommand extends CommandGroup {
	public AutoAlignAndDriveCommand(String speedPreferenceArg, String timePreferenceArg) {
		addSequential(new AutoGearAlignmentCommand());
		addSequential(new DriveForward(speedPreferenceArg, timePreferenceArg));
	}
}
