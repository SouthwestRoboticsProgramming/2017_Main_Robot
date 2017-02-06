package org.usfirst.frc.team2129.robot.commands;

import org.usfirst.frc.team2129.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ManualGearCommand extends Command {
	public ManualGearCommand(){
		requires(Robot.gearSubsystem);
	}
	
	public void execute(){
		SmartDashboard.putString("g_p_req_state", "NREQ");
		
		if (Robot.oi.thirdJoystick.getRawButton(4)){
			SmartDashboard.putString("g_p_req_state", "ON (4)");
			Robot.gearSubsystem.open();
		}
		
		if (Robot.oi.thirdJoystick.getRawButton(5)){
			SmartDashboard.putString("g_p_req_state", "OFF (5)");
			Robot.gearSubsystem.close();
		}
	}
	
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}