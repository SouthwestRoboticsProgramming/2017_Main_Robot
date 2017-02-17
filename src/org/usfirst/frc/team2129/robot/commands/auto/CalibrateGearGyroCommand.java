package org.usfirst.frc.team2129.robot.commands.auto;

import org.usfirst.frc.team2129.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CalibrateGearGyroCommand extends Command {
	Timer t;
	boolean d = false;
	static int init_count=0;
	
	public CalibrateGearGyroCommand(){
		requires(Robot.gearSubsystem);
		t=new Timer();
	}
	
	public void initialize(){
		t.reset();
		t.start();
		d=false;
		init_count+=1;
		SmartDashboard.putNumber("gear_zero_icount", init_count);
	}
	
	protected boolean isFinished() {
		return d;
	}
	
	public void execute(){
		SmartDashboard.putNumber("gear_zero_timer", t.get());
		if(t.get()>0.75 || d){
			Robot.gearSubsystem.zeroGyro();
			Robot.gearSubsystem.gearMotor.set(0);
			t.stop();
			d=true;
		}else{
			Robot.gearSubsystem.gearMotor.set(-0.4);
		}
	}
}