package org.usfirst.frc.team2129.robot.commands;

public class ManualClimbCommand extends ClimbCommand {

	public ManualClimbCommand(){
		requires(getClimberSubsystem());
	}
	
	protected boolean isFinished() {
		return false;
	}

	public void execute() {
		if (getLeftJoystick().getRawButton(1)) {
			getClimberSubsystem().setSpeed(-0.5);
		} else {
			getClimberSubsystem().setSpeed(0);
		}
	}

}
