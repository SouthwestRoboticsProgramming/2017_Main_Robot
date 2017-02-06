package org.usfirst.frc.team2129.robot.commands.auto;

import org.usfirst.frc.team2129.robot.Robot;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoOrientCommand extends Command {
	
	double target;
	double fuzz;
	long done_start_time=-1;
	long time_req;
	
	boolean bostonDynamicsMode;
	
	public AutoOrientCommand(double target, double fuzz, double time_req_f, boolean BDM){
		this.target=target;
		this.bostonDynamicsMode=BDM;
		this.fuzz=fuzz;
		this.time_req=1000;//(long)(time_req*1000.);
		SmartDashboard.putString("AUTO_ORIENT_COMMAND", "CONSTRUCTED");
		//requires(Robot.imuSubsystem);
		requires(Robot.drivetrainSubsystem);
	}
	
	private double getAngle(){
		double a = Robot.imuSubsystem.getZ()+target;
		while(a<-360)a+=360;
		if(a<0)a+=360;
		while(a>360)a-=360;
		return a;
	}
	
	private boolean onTarget(){
		if (Math.abs(getAngle())<fuzz) return true;
		if (Math.abs(getAngle()-360)<fuzz) return true;
		return false;
	}

	@Override
	protected boolean isFinished() {
		if(bostonDynamicsMode) return false;
		if(done_start_time==-1){
			if(onTarget()){
				done_start_time=System.currentTimeMillis();
			}
		}else{
			if(onTarget()){
				if((System.currentTimeMillis()-done_start_time)>time_req){
					return true;
				}
			}else{
				done_start_time=-1;
			}
		}
		return false;
	}
	
	public void execute(){
		SmartDashboard.putString("AUTO_ORIENT_COMMAND", "EXECUTING");
		double delta=getAngle();
		double dir = 1;
		if(delta>180){
			delta=360-delta;
			dir=-1;
		}
		double kP=Preferences.getInstance().getDouble("kP", 0.1);
		
		double calc = (delta/360)*kP;
		calc+=Preferences.getInstance().getDouble("kBASE", 0.1);
		
		if (calc>Preferences.getInstance().getDouble("kCAP", 0.4)){
			calc=Preferences.getInstance().getDouble("kCAP", 0.4);
		}
		
		calc*=dir;
		
		SmartDashboard.putNumber("AOC_Angle", getAngle());
		SmartDashboard.putNumber("calc", calc);
		SmartDashboard.putBoolean("ontarget", onTarget());
		SmartDashboard.putNumber("dst", done_start_time);
		SmartDashboard.putNumber("dtime", System.currentTimeMillis()-done_start_time);
		SmartDashboard.putNumber("treq", time_req);
		
		if(!onTarget()){
			Robot.drivetrainSubsystem.tankDrive(1*calc, -1*calc);
			SmartDashboard.putBoolean("filtered_running", true);
		}
		else{
			Robot.drivetrainSubsystem.tankDrive(0, 0);
			SmartDashboard.putBoolean("filtered_running", false);
		}
	}
	
	public void initialize(){
		SmartDashboard.putString("AUTO_ORIENT_COMMAND", "INITILIZING");
	}
	
	public void end(){
		SmartDashboard.putString("AUTO_ORIENT_COMMAND", "STOPPED");
	}

}
