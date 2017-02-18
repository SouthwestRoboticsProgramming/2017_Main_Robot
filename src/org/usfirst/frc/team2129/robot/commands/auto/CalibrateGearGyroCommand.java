package org.usfirst.frc.team2129.robot.commands.auto;

import org.usfirst.frc.team2129.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CalibrateGearGyroCommand extends Command {
	boolean d = false;
	
	public CalibrateGearGyroCommand(){
		requires(Robot.gearSubsystem);
	}
	
	public void initialize(){
		d=false;
	}
	
	protected boolean isFinished() {
		return d;
	}
	
	public void execute(){
		if(Robot.gearSubsystem.getRate()>0 || d){
			Robot.gearSubsystem.zeroAgle();
			Robot.gearSubsystem.gearMotor.set(0);
			d=true;
		}else{
			Robot.gearSubsystem.gearMotor.set(-0.4);
		}
	}
}