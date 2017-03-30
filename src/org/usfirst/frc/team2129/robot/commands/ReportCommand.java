package org.usfirst.frc.team2129.robot.commands;

public class ReportCommand extends Team2129Command {

	public ReportCommand() {
		requires(getImuSubsystem());
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	public void execute() {
		//TODO: Should we just loop through a collection of subsystems??
		setPeripheralDashboardValues();
		setImuDashboard();
		setDriveTrainDashboard();
		setGearDashboard();
		setClimberDashboard();
		
		resetSubsystems();
	}

	private void setImuDashboard() {
		getImuSubsystem().setDashboardValues();
	}

	private void setClimberDashboard() {
		getClimberSubsystem().setDashboardValues();
	}

	private void setGearDashboard() {
		getGearSubsystem().setDashboardValues();
	}

	private void setDriveTrainDashboard() {
		getDrivetrainSubsystem().setDashboardValues();
	}

	private void setPeripheralDashboardValues() {
		getPeripheralsSubsystem().setDashboardValues();
	}

	private void resetSubsystems() {
//		// TODO: probably should delegate to each subsystem
//		if (getLeftJoystick().getRawButton(12) || getRightJoystick().getRawButton(12)) {
//			getImuSubsystem().calibrate();
//			getGearSubsystem().zeroAngle();
//			getImuSubsystem().zero();
//			getDrivetrainSubsystem().getLeftIEncoder().zero();
//			getDrivetrainSubsystem().getRightIEncoder().zero();
//		}
	}

}
