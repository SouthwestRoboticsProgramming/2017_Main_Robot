package org.usfirst.frc.team2129.robot.commands;

import org.usfirst.frc.team2129.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ReportCommand extends Command {
	
	public ReportCommand(){
		requires(Robot.imuSubsystem);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void execute(){
		SmartDashboard.putBoolean("compressor_enabled", Robot.pneumaticsManagmentSubsystem.compressor.enabled());
		SmartDashboard.putBoolean("compressor_cth_f", Robot.pneumaticsManagmentSubsystem.compressor.getCompressorCurrentTooHighFault());
		SmartDashboard.putBoolean("compressor_cth_sf", Robot.pneumaticsManagmentSubsystem.compressor.getCompressorCurrentTooHighStickyFault());
		SmartDashboard.putBoolean("compressor_nc_f", Robot.pneumaticsManagmentSubsystem.compressor.getCompressorNotConnectedFault());
		SmartDashboard.putBoolean("compressor_nc_sf", Robot.pneumaticsManagmentSubsystem.compressor.getCompressorNotConnectedStickyFault());
		SmartDashboard.putBoolean("compressor_s_f", Robot.pneumaticsManagmentSubsystem.compressor.getCompressorShortedFault());
		SmartDashboard.putBoolean("compressor_s_sf", Robot.pneumaticsManagmentSubsystem.compressor.getCompressorShortedStickyFault());
		
		SmartDashboard.putString("ReportIMUCommand", "RUNNING");
		SmartDashboard.putNumber("imu_ax", Robot.imuSubsystem.getX());
		SmartDashboard.putNumber("imu_ay", Robot.imuSubsystem.getY());
		SmartDashboard.putNumber("imu_az", Robot.imuSubsystem.getZ());
		
		SmartDashboard.putNumber("left_encoder_traversal", Robot.drivetrainSubsystem.leftEncoder.getDistance());
		SmartDashboard.putNumber("left_encoder_speed", Robot.drivetrainSubsystem.leftEncoder.getRate());
		SmartDashboard.putNumber("right_encoder_traversal", Robot.drivetrainSubsystem.rightEncoder.getDistance());
		SmartDashboard.putNumber("right_encoder_speed", Robot.drivetrainSubsystem.rightEncoder.getRate());
		
		if(Robot.oi.leftJoystick.getRawButton(2)||Robot.oi.rightJoystick.getRawButton(2)){
			Robot.imuSubsystem.imu.calibrate();
			Robot.imuSubsystem.zero();
		}
		
		if(Robot.oi.leftJoystick.getRawButton(3)||Robot.oi.rightJoystick.getRawButton(3)){
			Robot.imuSubsystem.zero();
		}
		
		if(Robot.oi.leftJoystick.getRawButton(4)||Robot.oi.rightJoystick.getRawButton(4)){
			Robot.drivetrainSubsystem.leftEncoder.reset();
			Robot.drivetrainSubsystem.rightEncoder.reset();
		}
	}

}
