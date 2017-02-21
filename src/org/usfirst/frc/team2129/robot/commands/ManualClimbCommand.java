package org.usfirst.frc.team2129.robot.commands;

public class ManualClimbCommand extends ClimbCommand {

	public ManualClimbCommand(){
		requires(getSubsystem());
	}
	
	protected boolean isFinished() {
		return false;
	}

	public void execute() {
//		getSubsystem().setSpeed(Robot.oi.thirdJoystick.getY());
		getSubsystem().stopClimbing();
	}

}
