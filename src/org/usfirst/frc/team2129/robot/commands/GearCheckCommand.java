package org.usfirst.frc.team2129.robot.commands;

import org.usfirst.frc.team2129.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class GearCheckCommand extends Command{

	boolean finished;
	
	public GearCheckCommand() {
		requires(Robot.gearSubsystem);
		finished = false;
	}
	
	protected boolean isFinished() {
		return finished;
	}
	
	protected void execute() {
		Robot.gearSubsystem.checkForGear();
		finished = true;
	}

}
