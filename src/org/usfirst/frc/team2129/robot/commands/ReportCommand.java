package org.usfirst.frc.team2129.robot.commands;

import org.usfirst.frc.team2129.robot.Robot;

import com.ctre.CANTalon;

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
		SmartDashboard.putBoolean("compressor_enabled", Robot.peripheralsSubsystem.compressor.enabled());
		SmartDashboard.putBoolean("compressor_cth_f", Robot.peripheralsSubsystem.compressor.getCompressorCurrentTooHighFault());
		SmartDashboard.putBoolean("compressor_cth_sf", Robot.peripheralsSubsystem.compressor.getCompressorCurrentTooHighStickyFault());
		SmartDashboard.putBoolean("compressor_nc_f", Robot.peripheralsSubsystem.compressor.getCompressorNotConnectedFault());
		SmartDashboard.putBoolean("compressor_nc_sf", Robot.peripheralsSubsystem.compressor.getCompressorNotConnectedStickyFault());
		SmartDashboard.putBoolean("compressor_s_f", Robot.peripheralsSubsystem.compressor.getCompressorShortedFault());
		SmartDashboard.putBoolean("compressor_s_sf", Robot.peripheralsSubsystem.compressor.getCompressorShortedStickyFault());
		
		SmartDashboard.putString("ReportIMUCommand", "RUNNING");
		SmartDashboard.putNumber("imu_ax", Robot.imuSubsystem.getX());
		SmartDashboard.putNumber("imu_ay", Robot.imuSubsystem.getY());
		SmartDashboard.putNumber("imu_az", Robot.imuSubsystem.getZ());
		
		SmartDashboard.putNumber("left_encoder_traversal", Robot.drivetrainSubsystem.leftEncoder.getDistance());
		SmartDashboard.putNumber("left_encoder_speed", Robot.drivetrainSubsystem.leftEncoder.getRate());
		SmartDashboard.putNumber("right_encoder_traversal", Robot.drivetrainSubsystem.rightEncoder.getDistance());
		SmartDashboard.putNumber("right_encoder_speed", Robot.drivetrainSubsystem.rightEncoder.getRate());
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i<16; i++){
			sb.append(";");
			sb.append(Robot.peripheralsSubsystem.pdp.getCurrent(i));
		}
		
		SmartDashboard.putString("pdp_status", sb.toString());
		SmartDashboard.putNumber("pdp_total_current", Robot.peripheralsSubsystem.pdp.getTotalCurrent());
		SmartDashboard.putNumber("pdp_total_power", Robot.peripheralsSubsystem.pdp.getTotalPower());
		SmartDashboard.putNumber("pdp_voltage", Robot.peripheralsSubsystem.pdp.getVoltage());
		SmartDashboard.putNumber("pdp_total_energy", Robot.peripheralsSubsystem.pdp.getTotalEnergy());
		SmartDashboard.putNumber("pdp_ch[15]", Robot.peripheralsSubsystem.pdp.getCurrent(15));
		
		SmartDashboard.putNumber("gear_gyro_pos", Robot.gearSubsystem.getGyro());
		SmartDashboard.putBoolean("gear_light", Robot.gearSubsystem.gearLightSensor.get());
		
		SmartDashboard.putNumber("talon_pos", ((CANTalon)Robot.drivetrainSubsystem.rightGearboxMotor2).getPosition());
		SmartDashboard.putString("talon_staet", ((CANTalon)Robot.drivetrainSubsystem.rightGearboxMotor2).isSensorPresent(CANTalon.FeedbackDevice.CtreMagEncoder_Absolute).toString());
		
		if(Robot.oi.leftJoystick.getRawButton(2)||Robot.oi.rightJoystick.getRawButton(2)){
			Robot.imuSubsystem.imu.calibrate();
			Robot.gearSubsystem.gearGyro.calibrate();
			Robot.gearSubsystem.zeroGyro();
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
