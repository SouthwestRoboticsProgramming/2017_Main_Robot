package org.usfirst.frc.team2129.robot.commands;

import org.usfirst.frc.team2129.robot.Buttons;
import org.usfirst.frc.team2129.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Abstract class .. holds some shared methods. This would be a good place for
 * some logging.
 */
public abstract class Team2129Command extends Command implements Team2129GlobalInterface, Buttons {

	protected Joystick getLeftJoystick() {
		return Robot.oi.leftJoystick;
	}

	protected Joystick getRightJoystick() {
		return Robot.oi.rightJoystick;
	}
}
