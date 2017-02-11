package org.usfirst.frc.team2129.robot.commands.auto;

import org.usfirst.frc.team2129.robot.Robot;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoGearAlignmentCommand extends Command {
	
	String offsetKey = "L_OFF_X";
	
	public AutoGearAlignmentCommand(){
		requires(Robot.drivetrainSubsystem);
	}
	
	protected int getOffset(){
		return (int) SmartDashboard.getNumber(offsetKey, 0);
	}

	protected boolean isFinished() {
		return false;
	}
	
	public void execute(){
		int offset = getOffset();
		boolean side = offset>0;
		double speed=((double)Math.abs(offset)/90.)*Preferences.getInstance().getDouble("gear_align_spd", 0.3);
		speed+=Preferences.getInstance().getDouble("gear_align_base", 0.3);
		
		if(Math.max(Math.abs(Robot.drivetrainSubsystem.leftEncoder.getRate()),
				Math.abs(Robot.drivetrainSubsystem.rightEncoder.getRate()))<
				Preferences.getInstance().getDouble("gear_align_0fuzz", 4)){
			speed+=Preferences.getInstance().getDouble("gear_align_0bonus", 0.1);
		}
		
		
		speed*=Preferences.getInstance().getDouble("gear_align_emul", -1);
		SmartDashboard.putNumber("calc", speed);
		SmartDashboard.putBoolean("aga_side", side);
		
		if(Math.abs(getOffset())<Preferences.getInstance().getDouble("gear_align_fuzz", 2)){
			double sts=Preferences.getInstance().getDouble("gear_align_sts", -0.5);
			SmartDashboard.putBoolean("sts", true);
			Robot.drivetrainSubsystem.tankDrive(sts, sts);
		}else{
			SmartDashboard.putBoolean("sts", false);
			double opp_spd = Preferences.getInstance().getDouble("gear_align_opp", 0.2);
			Robot.drivetrainSubsystem.tankDrive(side?speed:opp_spd, side?opp_spd:speed);
		}
	}
}
