package org.usfirst.frc.team2129.robot.subsystems;

import org.usfirst.frc.team2129.robot.commands.ReportCommand;
import org.usfirst.frc.team2129.util.CalibratedADISIMU;

// IMU = Inertial Measurement Unit
public class IMUSubsystem extends Team2129Subsystem {

	private CalibratedADISIMU imu;

	public IMUSubsystem() {
		imu = new CalibratedADISIMU();
		zero();
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ReportCommand());
	}

	public void zero() {
		imu.zero();
	}

	public void calibrate() {
		imu.calibrate();
	}

	public double getX() {
		return imu.getX();
	}

	public double getY() {
		return imu.getY();
	}

	public double getZ() {
		return imu.getZ();
	}

	public void freeze() {
		imu.freeze();
	}

	protected void unFreezeImu() {
		imu.unfreeze();
	}

	public void setDashboardValues() {
		setSmartDashboard("ReportIMUCommand", "RUNNING");
		setSmartDashboard("imu_ax", getX());
		setSmartDashboard("imu_ay", getY());
		setSmartDashboard("imu_az", getZ());
	}
}
