package org.usfirst.frc.team2129.robot.commands.auto;

import org.usfirst.frc.team2129.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MoveGearToRecvCommand extends Command {
	PIDController gearPID;
	
	public MoveGearToRecvCommand(){
		requires(Robot.gearSubsystem);
		gearPID = new PIDController(0.1, 0.01, 0, Robot.gearSubsystem.getPIDSource(), Robot.gearSubsystem.gearMotor);
	}
	
	protected boolean isFinished() {
		return gearPID.onTarget();
	}
	
	public void initialize(){
		
		gearPID.setSetpoint(Preferences.getInstance().getDouble("gear_recv_pos", 60));
		gearPID.setAbsoluteTolerance(Preferences.getInstance().getDouble("gear_recv_tol", 3));
		gearPID.setPID(
			Preferences.getInstance().getDouble("gear_recv_p", 0.1),
			Preferences.getInstance().getDouble("gear_recv_i", 0.01),
			Preferences.getInstance().getDouble("gear_recv_d", 0)
		);
		gearPID.setOutputRange(-0.5, 0.5);
		gearPID.enable();
	}
	
	public void execute(){
		SmartDashboard.putNumber("gear_recv_out", gearPID.get());
		SmartDashboard.putNumber("gear_recv_delta", gearPID.getDeltaSetpoint());
		SmartDashboard.putNumber("gear_recv_ps_rd", Robot.gearSubsystem.getPIDSource().pidGet());
	}
	
	public void end(){
		gearPID.disable();
	}
}
