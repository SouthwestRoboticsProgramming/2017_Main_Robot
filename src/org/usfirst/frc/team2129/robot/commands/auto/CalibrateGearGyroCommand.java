package org.usfirst.frc.team2129.robot.commands.auto;

import org.usfirst.frc.team2129.robot.Robot;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CalibrateGearGyroCommand extends Command {
	boolean d = false;
	boolean started = false;
	
	public CalibrateGearGyroCommand(){
		requires(Robot.gearSubsystem);
	}
	
	public void initialize(){
		d=false;
		started=false;
	}
	
	protected boolean isFinished() {
		return d;
	}
	
	public void execute(){
		double rate = Preferences.getInstance().getDouble("gear_0_rate", -0.3);
		if(started){
			if(Math.abs(Robot.gearSubsystem.getRate())<1 || d){
				Robot.gearSubsystem.zeroAgle();
				Robot.gearSubsystem.gearMotor.set(0);
				d=true;
				SmartDashboard.putString("gearcalibrate_state", "1_done");
			}else{
				Robot.gearSubsystem.gearMotor.set(rate);
			}
		}else{
			if(Math.abs(Robot.gearSubsystem.getRate())>0){
				started=true;
			}
			Robot.gearSubsystem.gearMotor.set(rate);
		}
	}
}