package org.usfirst.frc.team2129.robot.subsystems;

import org.usfirst.frc.team2129.robot.map.MechanumRobotMap;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;

public class MechanumDrivetrainSubsystem extends Team2129DrivetrainSubsystem {
	private SpeedController leftFrontGearboxMotor1;
	private SpeedController rightFrontGearboxMotor1;
	private SpeedController leftRearGearboxMotor1;
	private SpeedController rightRearGearboxMotor1;
	private RobotDrive robotDrive;
	// for now ...
	private MechanumRobotMap map = new MechanumRobotMap();

	public MechanumDrivetrainSubsystem() {
		leftFrontGearboxMotor1 = map.getLeftFrontMotor().get();
		rightFrontGearboxMotor1 = map.getRightFrontMotor().get();
		leftRearGearboxMotor1 = map.getRightFrontMotor().get();
		rightRearGearboxMotor1 = map.getRightRearMotor().get();
		robotDrive = new RobotDrive(leftFrontGearboxMotor1, leftRearGearboxMotor1, rightFrontGearboxMotor1, rightRearGearboxMotor1);
	}

	public void tankDrive(double left, double right) {
		robotDrive.tankDrive(left, right);
	}

	public void arcadeDrive(double moveValue, double rotateValue) {
		robotDrive.arcadeDrive(moveValue, rotateValue, true);
	}

	public void setReversed(boolean state) {
		//no op
	}

	public void setShift(boolean state) {
		//no op
	}
}
