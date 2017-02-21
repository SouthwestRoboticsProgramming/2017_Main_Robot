package org.usfirst.frc.team2129.robot.commands;

import org.usfirst.frc.team2129.robot.subsystems.ClimberSubsystem;

public abstract class ClimbCommand extends Team2129Command {

	protected ClimberSubsystem getSubsystem() {
		return getClimberSubsystem();
	}

	protected void stopClimbing() {
		getSubsystem().stopClimbing();
	}
}
