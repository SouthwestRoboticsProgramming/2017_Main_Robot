package org.usfirst.frc.team2129.robot.subsystems;

import org.usfirst.frc.team2129.robot.commands.ManualBlinkCommand;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Direction;
import edu.wpi.first.wpilibj.Relay.Value;

public class FlashyLightsSubsystem extends Team2129Subsystem {
	private static final Value OFF = Relay.Value.kOff;
	private static final Value ON = Relay.Value.kForward;
	private static final Direction FORWARD = Relay.Direction.kForward;
	private Relay left;
	private Relay right;

	public FlashyLightsSubsystem() {
		left = new Relay(getLeftFlashyLight(), FORWARD);
		right = new Relay(getRightFlashyLight(), FORWARD);
	}

	protected void initDefaultCommand() {
		setDefaultCommand(new ManualBlinkCommand());
	}

	public void setLeft(boolean val) {
		left.set(val ? ON : OFF);
	}

	public void setRight(boolean val) {
		right.set(val ? ON : OFF);
	}

	public void setLeftOrRight(boolean val) {
		(val ? left : right).set(ON);
		(val ? right : left).set(OFF);
	}
}
