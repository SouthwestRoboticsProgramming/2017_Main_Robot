package org.usfirst.frc.team2129.robot.commands;

public class ManualGearCommand extends Team2129Command {
	public ManualGearCommand() {
		requires(getGearSubsystem());
	} 
	public void execute() {
		//getGearSubsystem().setGearSolenoid(getLeftJoystick().getRawButton(3)||getRightJoystick().getRawButton(3));
		//getGearSubsystem().setGearMotor(getThirdJoystick().getX());
	}

	protected boolean isFinished() {
		return false;
	}
}
