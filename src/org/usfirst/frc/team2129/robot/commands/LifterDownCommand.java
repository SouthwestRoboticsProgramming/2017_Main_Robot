package org.usfirst.frc.team2129.robot.commands;

import org.usfirst.frc.team2129.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LifterDownCommand extends Command {

	protected boolean isFinished() {
		return false;
	}
	
	public LifterDownCommand() {
		requires(Robot.lifterSubsystem);
	}
	
	protected void execute() {
		Robot.lifterSubsystem.GoDown();
	}
	
}
