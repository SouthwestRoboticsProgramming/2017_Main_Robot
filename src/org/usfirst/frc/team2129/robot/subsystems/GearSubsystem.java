package org.usfirst.frc.team2129.robot.subsystems;

import org.usfirst.frc.team2129.robot.Robot;
import org.usfirst.frc.team2129.robot.commands.ManualGearCommand;
import org.usfirst.frc.team2129.util.encoderdesc.iencoder.IEncoder;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;

public class GearSubsystem extends Team2129Subsystem {
	// Inputs
	private DigitalInput gearLightSensor;
	private IEncoder gearEncoder;

	// Outputs
	private Solenoid gearSolenoid;
	private SpeedController gearMotor;

	public GearSubsystem() {
		gearMotor = Robot.map.GearMotor.get();
		gearSolenoid = new Solenoid(Robot.map.gearSolenoid);
		gearLightSensor = new DigitalInput(Robot.map.gearLightSensor);
		gearEncoder = Robot.map.gearEncoder.get();
	}

	protected void initDefaultCommand() {
		setDefaultCommand(new ManualGearCommand());
	}

	public boolean getGearGood() {
		return gearLightSensor.get();
	}

	public void zeroAngle() {
		gearEncoder.zero();
	}

	public double getAngle() {
		return gearEncoder.getDistance();
	}

	public double getRate() {
		return gearEncoder.getRate();
	}

	public PIDSource getPIDSource() {
		return gearEncoder;
	}

	public SpeedController getGearMotor() {
		return gearMotor;
	}

	public Solenoid getGearSolenoid() {
		return gearSolenoid;
	}

	public DigitalInput getGearLightSensor() {
		return gearLightSensor;
	}

	public void setDashboardValues() {
		setSmartDashboard("gear_enc_pos", getAngle());
		setSmartDashboard("gear_enc_rate", getRate());
		setSmartDashboard("gear_light", getGearLightSensor().get());
	}
	
}
