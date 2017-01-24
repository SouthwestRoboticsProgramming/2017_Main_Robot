package org.usfirst.frc.team2129.robot.commands;

import org.usfirst.frc.team2129.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ReportIMUCommand extends Command {

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public ReportIMUCommand(){
		requires(Robot.imuSubsystem);
	}
	
	public void execute(){
		SmartDashboard.putString("ReportIMUCommand", "RUNNING");
		SmartDashboard.putNumber("imu_ax", Robot.imuSubsystem.getX());
		SmartDashboard.putNumber("imu_ay", Robot.imuSubsystem.getY());
		SmartDashboard.putNumber("imu_az", Robot.imuSubsystem.getZ());
		SmartDashboard.putNumber("imu_rawx", Robot.imuSubsystem.imu.getAngleX());
		SmartDashboard.putNumber("imu_rawy", Robot.imuSubsystem.imu.getAngle());
		SmartDashboard.putNumber("imu_rawz", Robot.imuSubsystem.imu.getAngleZ());
		
		if(Robot.oi.leftJoystick.getRawButton(2)||Robot.oi.rightJoystick.getRawButton(2)){
			Robot.imuSubsystem.imu.calibrate();
			Robot.imuSubsystem.zero();
		}
	}

}
