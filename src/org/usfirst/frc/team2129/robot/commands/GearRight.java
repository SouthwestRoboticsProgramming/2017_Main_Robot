package org.usfirst.frc.team2129.robot.commands;

import org.usfirst.frc.team2129.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class GearRight extends Command{

	boolean finished;
	
	public GearRight() {
		requires(Robot.gearSubsystem);
		finished = false;
	}
	
	protected boolean isFinished() {
		return finished;
	}
	
	public void execute() {
		Robot.gearSubsystem.rotateRight();
		finished = true;
	}

}
