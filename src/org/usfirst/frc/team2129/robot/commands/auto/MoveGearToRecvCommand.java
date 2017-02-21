package org.usfirst.frc.team2129.robot.commands.auto;

import org.usfirst.frc.team2129.robot.commands.Team2129Command;

import edu.wpi.first.wpilibj.PIDController;

public class MoveGearToRecvCommand extends Team2129Command {
	private PIDController gearPID;

	public MoveGearToRecvCommand() {
		requires(getGearSubsystem());
		gearPID = new PIDController(0.1, 0.01, 0, getGearSubsystem().getPIDSource(), getGearMotor());
	}

	protected boolean isFinished() {
		return gearPID.onTarget();
	}

	public void initialize() {
		gearPID.setSetpoint(getPreferences().getDouble("gear_recv_pos", 60));
		gearPID.setAbsoluteTolerance(getPreferences().getDouble("gear_recv_tol", 3));
		gearPID.setPID(getPreferences().getDouble("gear_recv_p", 0.1), getPreferences().getDouble("gear_recv_i", 0.01),
				getPreferences().getDouble("gear_recv_d", 0));
		gearPID.setOutputRange(-0.5, 0.5);
		gearPID.enable();
	}

	public void execute() {
		setSmartDashboard("gear_recv_out", gearPID.get());
		setSmartDashboard("gear_recv_delta", gearPID.getDeltaSetpoint());
		setSmartDashboard("gc_state", "2_running");
		setSmartDashboard("gear_recv_ps_rd", getGearSubsystem().getPIDSource().pidGet());
	}

	public void end() {
		gearPID.disable();
		setSmartDashboard("gc_state", "2_done");
	}
}
