package org.usfirst.frc.team2129.robot.commands.auto;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class FullAutoCommand extends CommandGroup {
	private static final double SPEED = -0.6;
	private static final double TIME_IN_SECONDS = 1.7;
	private StartPosition startPosition;

	public FullAutoCommand(String startPosition) {
//		this.startPosition = StartPosition.get(getPreferences().getString("auto_start_position", "MIDDLE"));
	}
 
	public StartPosition getStartPosition() {
		if (startPosition == null)
			startPosition = StartPosition.MIDDLE;
		return startPosition;
	}
	
	public FullAutoCommand() {
		this.startPosition = StartPosition.get(getPreferences().getString("auto_start_position", "MIDDLE"));
		System.err.println("START POSITION = " + getStartPosition());
		if (!getStartPosition().isMiddle()) {
			addSequential(new DriveForward(SPEED, TIME_IN_SECONDS));
			addSequential(new LookForPegCommand(getStartPosition().isRight()));
		}
		addSequential(new AutoGearAlignmentCommand());
		// Final little push to get it on the peg
		addSequential(new DriveForward(SPEED, 3));
	}
 
	private Preferences getPreferences() {
		return Preferences.getInstance();
	}
}