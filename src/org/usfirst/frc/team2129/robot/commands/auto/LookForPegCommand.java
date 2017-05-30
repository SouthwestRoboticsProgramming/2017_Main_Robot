package org.usfirst.frc.team2129.robot.commands.auto;

import org.usfirst.frc.team2129.robot.commands.Team2129Command;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// We're just going to spin in a particular direction until we SEE the peg
public class LookForPegCommand extends Team2129Command {
	private static final double SPIN_SPEED = 0.4;
	private boolean spinLeft;

	public LookForPegCommand(boolean spinLeft) {
		super();
		requires(getDrivetrainSubsystem());
		this.spinLeft = spinLeft;
	}

	public void initialize() {
	}

	public void execute() {
		if (spinLeft)
			spinLeft();
		else
			spinRight();
	}

	private void spinLeft() {
		getDrivetrainSubsystem().tankDrive(-SPIN_SPEED, SPIN_SPEED);
	}

	private void spinRight() {
		getDrivetrainSubsystem().tankDrive(SPIN_SPEED, -SPIN_SPEED);
	}

	@Override
	protected boolean isFinished() {
		return SmartDashboard.getString(AutoGearAlignmentCommand.OFFSET_OK, "NO").equals("OK");
	}
}
