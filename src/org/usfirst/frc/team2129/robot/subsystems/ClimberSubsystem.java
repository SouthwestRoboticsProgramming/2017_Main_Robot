package org.usfirst.frc.team2129.robot.subsystems;

import org.usfirst.frc.team2129.robot.Robot;
import org.usfirst.frc.team2129.robot.commands.ManualClimbCommand;
import org.usfirst.frc.team2129.util.encoderdesc.iencoder.IEncoder;

import edu.wpi.first.wpilibj.SpeedController;

public class ClimberSubsystem extends Team2129Subsystem {

	private SpeedController climbMotor1;
	private SpeedController climbMotor2;
	public IEncoder encoder;

	public ClimberSubsystem() {
		climbMotor1 = Robot.map.LiftMotor1.get();
		climbMotor2 = Robot.map.LiftMotor2.get();
		encoder = Robot.map.climbEncoder.get();
	}

	protected void initDefaultCommand() {
		setDefaultCommand(new ManualClimbCommand());
	}

	public void setSpeed(double speed) {
		climbMotor1.set(speed);
		climbMotor2.set(speed);
	}

	public void stopClimbing() {
		setSpeed(0);
	}

	public void setDashboardValues() {
		setSmartDashboard("climb_pos", encoder.getDistance());
		setSmartDashboard("climb_rate", encoder.getRate());
	}
}
