package org.usfirst.frc.team2129.robot.commands;

import org.usfirst.frc.team2129.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ManualClimbCommand extends Command {

	public ManualClimbCommand(){
		requires(Robot.climberSubsystem);
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	public void execute(){
		Robot.climberSubsystem.setSpeed(Robot.oi.thirdJoystick.getY());
	}

}
