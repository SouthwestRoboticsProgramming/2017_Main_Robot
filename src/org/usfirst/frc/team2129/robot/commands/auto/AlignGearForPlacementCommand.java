package org.usfirst.frc.team2129.robot.commands.auto;

import org.usfirst.frc.team2129.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AlignGearForPlacementCommand extends Command {
	PIDController gearPID;
	Timer t;
	double target;
	
	public AlignGearForPlacementCommand(){
		requires(Robot.gearSubsystem);
		gearPID = new PIDController(0.1, 0.01, 0, Robot.gearSubsystem.getPIDSource(), Robot.gearSubsystem.gearMotor);
		t=new Timer();
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	public void initialize(){
		target=Preferences.getInstance().getDouble("gear_recv_pos", 45);
		gearPID.setSetpoint(target);
		gearPID.setAbsoluteTolerance(Preferences.getInstance().getDouble("gear_recv_tol", 3));
		gearPID.setPID(
			Preferences.getInstance().getDouble("gear_recv_p", 0.1),
			Preferences.getInstance().getDouble("gear_recv_i", 0.01),
			Preferences.getInstance().getDouble("gear_recv_d", 0)
		);
		gearPID.setOutputRange(-0.5, 0.5);
		gearPID.enable();
		t.reset();
		t.start();
	}
	
	public void execute(){
		SmartDashboard.putNumber("gear_recv_out", gearPID.get());
		SmartDashboard.putNumber("gear_recv_delta", gearPID.getDeltaSetpoint());
		SmartDashboard.putNumber("gear_recv_ps_rd", Robot.gearSubsystem.getPIDSource().pidGet());
		
		SmartDashboard.putNumber("gear_ali_target", target);
		
		if(!Robot.gearSubsystem.getGearGood() && gearPID.onTarget()){
			if(t.get()>Preferences.getInstance().getDouble("gear_rot_time", 0.1)){
				t.reset();
				t.start();
				target+=Preferences.getInstance().getDouble("gear_rot_delta", 3);
				gearPID.setSetpoint(target);
			}
		}
	}
	
	public void end(){
		gearPID.disable();
	}
}
