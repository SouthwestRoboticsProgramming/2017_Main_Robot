package org.usfirst.frc.team2129.robot.subsystems;

import org.usfirst.frc.team2129.robot.Robot;
import org.usfirst.frc.team2129.robot.commands.UserDriveCommand;
import org.usfirst.frc.team2129.util.encoderdesc.iencoder.IEncoder;
import org.usfirst.frc.team2129.util.speedcontrollers.SplitSpeedController;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;

public class DrivetrainSubsystem extends Team2129DrivetrainSubsystem {
	public static final String SPEED_MULTIPLIER = "speed_multiplier";
	public static final String SHIFT_SPEED_MULTIPLIER = "shift_speed_multiplier";

	private boolean reversed;

	private SpeedController leftGearboxMotor1;
	private SpeedController leftGearboxMotor2;
	private SplitSpeedController leftGearbox;

	private SpeedController rightGearboxMotor1;
	private SpeedController rightGearboxMotor2;
	private SplitSpeedController rightGearbox;

	private Solenoid shifter;
	private RobotDrive robotDrive;

	private IEncoder leftEncoder;
	private IEncoder rightEncoder;

	public DrivetrainSubsystem() {
		reversed = false;
		// TODO: Huh? Left == right?
		leftGearboxMotor1 = Robot.map.RightMotor1.get();
		leftGearboxMotor2 = Robot.map.RightMotor2.get();
		leftGearbox = new SplitSpeedController(leftGearboxMotor1, leftGearboxMotor2);

		rightGearboxMotor1 = Robot.map.LeftMotor1.get();
		rightGearboxMotor2 = Robot.map.LeftMotor2.get();
		rightGearbox = new SplitSpeedController(rightGearboxMotor1, rightGearboxMotor2);

		shifter = new Solenoid(Robot.map.shifter);

		robotDrive = new RobotDrive(leftGearbox, rightGearbox);

		leftEncoder = Robot.map.leftEncoder.get();
		rightEncoder = Robot.map.rightEncoder.get();
	}

	protected void initDefaultCommand() {
		setDefaultCommand(new UserDriveCommand());
	}

	public void tankDrive(double left, double right) {
//		log("LEFT = " + left + " RIGHT = " + right);
		if (reversed) {
			robotDrive.tankDrive(left * -1, right * -1);
		} else {
			robotDrive.tankDrive(left, right);
		}
		double dmz = getPreferences().getDouble("gy_mute_zone", 0.02);

		if (Math.abs(left) < dmz && Math.abs(right) < dmz) {
			if (getPreferences().getBoolean("dynamic_gy_mute", false)) {
				Robot.imuSubsystem.freeze();
				setSmartDashboard("dynamic_gy_freeze", true);
			}
		} else {
			Robot.imuSubsystem.unFreezeImu();
			setSmartDashboard("dynamic_gy_freeze", false);
		}
	}

	public void arcadeDrive(double moveValue, double rotateValue) {
		System.err.println("ARCADE: move=" + moveValue + " rotate=" + rotateValue);
		robotDrive.arcadeDrive(moveValue, rotateValue, true);
	}

	public void setReversed(boolean state) {
		reversed = state;
	}

	public void setShift(boolean state) {
		shifter.set(state);
	}

	public IEncoder getLeftIEncoder() {
		return leftEncoder;
	}

	public IEncoder getRightIEncoder() {
		return rightEncoder;
	}

	public void setDashboardValues() {
		setSmartDashboard("left_encoder_traversal", getLeftIEncoder().getDistance());
		setSmartDashboard("left_encoder_speed", getLeftIEncoder().getRate());
		setSmartDashboard("right_encoder_traversal", getRightIEncoder().getDistance());
		setSmartDashboard("right_encoder_speed", getRightIEncoder().getRate());

		// Autonomous mode
		setSmartDashboard("auto_drive_speed", 0.5);
		setSmartDashboard("auto_drive_time", 2);
	}
}
