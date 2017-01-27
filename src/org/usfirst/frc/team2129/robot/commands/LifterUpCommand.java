package org.usfirst.frc.team2129.robot.commands;

import org.usfirst.frc.team2129.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LifterUpCommand extends Command {

	protected boolean isFinished() {
		return false;
	}
	
	public LifterUpCommand() {
		requires(Robot.lifterSubsystem);
	}
	
	public void execute() {
		Robot.lifterSubsystem.GoUp();
	}

}
