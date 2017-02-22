package org.usfirst.frc.team2129.robot.commands;

public class ManualGearCommand extends Team2129Command {
	public ManualGearCommand() {
		requires(getGearSubsystem());
	}

	public void execute() {
		getGearSolenoid().set(getLeftJoystick().getRawButton(3)||getRightJoystick().getRawButton(3));
		// setSmartDashboard("j3b3", Robot.oi.thirdJoystick.getRawButton(3));
		setSmartDashboard("gear_real", getGearSolenoid().get());
		getGearMotor().set(getThirdJoystick().getX());
	}

	protected boolean isFinished() {
		return false;
	}
}
