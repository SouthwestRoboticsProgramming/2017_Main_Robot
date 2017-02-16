package org.usfirst.frc.team2129.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;

public class PeripheralsManagmentSubsystem extends Subsystem {
	public Compressor compressor = new Compressor();
	public PowerDistributionPanel pdp = new PowerDistributionPanel();
	
	public PeripheralsManagmentSubsystem(){
		compressor.setClosedLoopControl(true);
	}
	
	protected void initDefaultCommand() {}
}
