package org.usfirst.frc.team2129.robot.commands;

import org.usfirst.frc.team2129.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LifterStop extends Command {
	
	protected boolean isFinished() {
		return false;
	}
	
	public LifterStop() {
		requires(Robot.lifterSubsystem);
	}
	
	protected void execute() {
		Robot.lifterSubsystem.Stop();
	}

}
