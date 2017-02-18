package org.usfirst.frc.team2129.robot.commands;

import org.usfirst.frc.team2129.robot.Robot;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutomatedClimbCommand extends Command {
	
	Timer t;
	boolean d;
	boolean started;
	
	public AutomatedClimbCommand(){
		requires(Robot.climberSubsystem);
		t=new Timer();
		t.reset();
		t.stop();
	}
	
	public void initialize(){
		d=false;
		started=false;
	}

	public void execute(){
		SmartDashboard.putNumber("auto_climb_time", t.get());
		
		if(Math.abs(Robot.climberSubsystem.encoder.getRate())<Preferences.getInstance().getDouble("auto_climb_off_threshold", 1)){
			if(!started){
				t.reset();
				t.start();
				started=true;
			}
		}else{
			started=false;
			t.reset();
			t.stop();
		}
		
		if(t.get()>Preferences.getInstance().getDouble("auto_climb_done_time", 0.25)){
			Robot.climberSubsystem.setSpeed(0);
			d=true;
		}else{
			Robot.climberSubsystem.setSpeed(Preferences.getInstance().getDouble("auto_climb_speed", 0.6));
		}
	}
	
	protected boolean isFinished() {
		return d;
	}

}
