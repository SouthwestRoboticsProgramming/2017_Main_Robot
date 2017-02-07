package org.usfirst.frc.team2129.robot.subsystems;

import org.usfirst.frc.team2129.robot.Robot;
import org.usfirst.frc.team2129.robot.commands.ManualBlinkCommand;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

public class FlashyLightsSubsystem extends Subsystem {
	
	Relay left;
	Relay right;
	
	public FlashyLightsSubsystem(){
		left = new Relay(Robot.map.FlashyLightLeft, Relay.Direction.kForward);
		right = new Relay(Robot.map.FlashyLightRight, Relay.Direction.kForward);
	}

	protected void initDefaultCommand() {
		setDefaultCommand(new ManualBlinkCommand());
	}
	
	public void setLeft(boolean b){
		left.set(b?Relay.Value.kForward:Relay.Value.kOff);
	}
	
	public void setRight(boolean b){
		right.set(b?Relay.Value.kForward:Relay.Value.kOff);
	}
	
	public void setLeftOrRight(boolean l){
		(l?left:right).set(Relay.Value.kForward);
		(l?right:left).set(Relay.Value.kOff);
	}

}
