package org.usfirst.frc.team2129.robot.commands;

public class SetReversed extends Team2129Command {
	
	private boolean finished;
	
	public SetReversed() {
		requires(getDrivetrainSubsystem());
		finished = false;
	}
	
	protected boolean isFinished() {
		return finished;
	}
	
	public void execute() {
		getDrivetrainSubsystem().setReversed(true);
		finished = true;
	}
	
}
