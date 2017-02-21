package org.usfirst.frc.team2129.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CalibrateAndMoveGear extends CommandGroup {
	public CalibrateAndMoveGear(){
		addSequential(new CalibrateGearGyroCommand());
		addSequential(new MoveGearToRecvCommand());
	}
}
