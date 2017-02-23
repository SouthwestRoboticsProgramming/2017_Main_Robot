package org.usfirst.frc.team2129.robot.subsystems;

import org.usfirst.frc.team2129.robot.Robot;
import org.usfirst.frc.team2129.robot.commands.UserDriveCommand;
import org.usfirst.frc.team2129.util.encoderdesc.iencoder.IEncoder;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;

public class DrivetrainSubsystem extends Team2129Subsystem {
	public static final String SPEED_MULTIPLIER = "speed_multiplier";

	public static final String SHIFT_SPEED_MULTIPLIER = "shift_speed_multiplier";

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

	protected void initDefaultCommand() {
		setDefaultCommand(new UserDriveCommand());
	}

	public DrivetrainSubsystem() {
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

	public void tankDrive(double left, double right) {
		robotDrive.tankDrive(left, right);
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
	}
}
