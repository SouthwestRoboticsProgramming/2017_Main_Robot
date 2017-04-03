package org.usfirst.frc.team2129.robot.commands;

public class SetForward extends Team2129Command {
	
	private boolean finished;
	
	public SetForward() {
		requires(getDrivetrainSubsystem());
		finished = false;
	}
	
	protected boolean isFinished() {
		return finished;
	}
	
	public void execute() {
		getDrivetrainSubsystem().setReversed(false);
	}
}
