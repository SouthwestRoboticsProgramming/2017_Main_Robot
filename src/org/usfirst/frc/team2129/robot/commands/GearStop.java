package org.usfirst.frc.team2129.robot.commands;

import org.usfirst.frc.team2129.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class GearStop extends Command {
	
	boolean finished;
	
	public GearStop() {
		requires(Robot.gearSubsystem);
		finished = false;
	}

	protected boolean isFinished() {
		return finished;
	}
	
	public void execute() {
		Robot.gearSubsystem.rotateStop();
		finished = true;
	}
}
