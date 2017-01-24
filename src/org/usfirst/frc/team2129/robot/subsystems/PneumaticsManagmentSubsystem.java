package org.usfirst.frc.team2129.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class PneumaticsManagmentSubsystem extends Subsystem {
	Compressor compressor = new Compressor(0);
	
	public PneumaticsManagmentSubsystem(){
		compressor.setClosedLoopControl(true);
	}
	
	protected void initDefaultCommand() {}
}
