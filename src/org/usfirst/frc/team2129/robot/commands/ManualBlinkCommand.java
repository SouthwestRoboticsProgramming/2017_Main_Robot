package org.usfirst.frc.team2129.robot.commands;

import org.usfirst.frc.team2129.robot.Robot;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ManualBlinkCommand extends Command {
	
	Timer t;
	Timer lt;
	Timer rt;
	boolean state=false;
	boolean lstate=false;
	boolean rstate=false;
	
	public ManualBlinkCommand(){
		requires(Robot.lightsSubsystem);
		t=new Timer();
		t.start();
		lt=new Timer();
		lt.start();
		rt=new Timer();
		rt.start();
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	public void execute(){
		
		
		String mode=Preferences.getInstance().getString("mode", "off");
		SmartDashboard.putString("mode_o", mode);
		
		if(mode.equals("flash")){
			SmartDashboard.putNumber("timer_t", t.get());
			SmartDashboard.putBoolean("time_s", state);
			double time=Preferences.getInstance().getDouble("ftime", 1);
			if(t.get()>time){
				state=!state;
				Robot.lightsSubsystem.setLeftOrRight(state);
				t.reset();
				t.start();
			}
		}else if(mode.equals("drive")){
			double lrate = (1-Math.abs((Robot.oi.leftJoystick.getY())))*Preferences.getInstance().getDouble("stime", 1);;
			double rrate = (1-Math.abs((Robot.oi.rightJoystick.getY())))*Preferences.getInstance().getDouble("stime", 1);
					
			if(lt.get()>lrate){
				lstate=!lstate;
				Robot.lightsSubsystem.setLeft(lstate);
				lt.reset();
				lt.start();
			}
			
			if(rt.get()>rrate){
				rstate=!rstate;
				Robot.lightsSubsystem.setRight(rstate);
				rt.reset();
				rt.start();
			}
		}else{
			Robot.lightsSubsystem.setLeft(Robot.oi.rightJoystick.getRawButton(8));
			Robot.lightsSubsystem.setRight(Robot.oi.rightJoystick.getRawButton(9));
		}
	}
}
