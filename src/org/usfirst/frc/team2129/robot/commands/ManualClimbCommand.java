package org.usfirst.frc.team2129.robot.commands;

public class ManualClimbCommand extends ClimbCommand {

	public ManualClimbCommand(){
		requires(getClimberSubsystem());
	}
	
	protected boolean isFinished() {
		return false;
	}

	public void execute() {
		if (getRightJoystick().getRawButton(AUTO_CLIMB_BUTTON)) {
			getClimberSubsystem().setSpeed(0.5);
		} else if (getRightJoystick().getRawButton(11)) {
			getClimberSubsystem().setSpeed(1.0);
		} else {
			getClimberSubsystem().setSpeed(0);
		}
	}

}
