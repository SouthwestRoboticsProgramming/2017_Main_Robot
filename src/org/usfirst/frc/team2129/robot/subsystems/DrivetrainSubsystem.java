package org.usfirst.frc.team2129.robot.subsystems;

import org.usfirst.frc.team2129.robot.commands.UserDriveCommand;
import org.usfirst.frc.team2129.util.encoderdesc.iencoder.IEncoder;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;

public class DrivetrainSubsystem extends Team2129Subsystem {
	public static final String SPEED_MULTIPLIER = "speed_multiplier";

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

	private AnalogInput Ultrasonic1;

	// Mark has a plan for these
	private DigitalInput LightSensorLeft;
	private DigitalInput LightSensorRight;

	protected void initDefaultCommand() {
		setDefaultCommand(new UserDriveCommand());
	}

	public DrivetrainSubsystem() {
		// TODO: Huh? Left == right?
		leftGearboxMotor1 = getRightMotor1();
		leftGearboxMotor2 = getRightMotor2();
		leftGearbox = new SplitSpeedController(leftGearboxMotor1, leftGearboxMotor2);

		rightGearboxMotor1 = getLeftMotor1();
		rightGearboxMotor2 = getLeftMotor2();
		rightGearbox = new SplitSpeedController(rightGearboxMotor1, rightGearboxMotor2);

		shifter = new Solenoid(getShifter());

		robotDrive = new RobotDrive(leftGearbox, rightGearbox);

		leftEncoder = getLeftEncoder();
		rightEncoder = getRightEncoder();
		// Ultrasonic1 = new AnalogInput(RobotMap.Ultrasonic);//needs port
		// LightSensorLeft = new DigitalInput(RobotMap.DriveLightLeft);
		// LightSensorRight = new DigitalInput(RobotMap.DriveLightRight);
	}

	public void tankDrive(double left, double right) {
		robotDrive.tankDrive(left, right);
		double dmz = getPreferences().getDouble("gy_mute_zone", 0.02);
		
		if (Math.abs(left) < dmz && Math.abs(right) < dmz) {
			if (getPreferences().getBoolean("dynamic_gy_mute", false)) {
				freezeImu();
				setSmartDashboard("dynamic_gy_freeze", true);
			}
		} else {
			unFreezeImu();
			setSmartDashboard("dynamic_gy_freeze", false);
		}
	}

	// Mark has a plan for all of this...
	public int getValue() {
		return Ultrasonic1.getValue();
	}

	public double getVoltage() {
		return Ultrasonic1.getVoltage();
	}

	public boolean getLeft() {
		return LightSensorLeft.get();
	}

	public boolean getRight() {
		return LightSensorRight.get();
	}

	public void setShift(boolean state) {
		shifter.set(state);
	}

	public IEncoder getLeftEncoder() {
		return leftEncoder;
	}

	public IEncoder getRightEncoder() {
		return rightEncoder;
	}

	public void setDashboardValues() {
		setSmartDashboard("left_encoder_traversal", getLeftEncoder().getDistance());
		setSmartDashboard("left_encoder_speed", getLeftEncoder().getRate());
		setSmartDashboard("right_encoder_traversal", getRightEncoder().getDistance());
		setSmartDashboard("right_encoder_speed", getRightEncoder().getRate());

		// TODO: Is this just an init for the ReportCommand?
		setSmartDashboard(SPEED_MULTIPLIER, 0.7d);
	}
}
