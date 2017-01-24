package org.usfirst.frc.team2129.robot.subsystems;

import org.usfirst.frc.team2129.robot.commands.ReportIMUCommand;

import com.analog.adis16448.frc.ADIS16448_IMU;

import edu.wpi.first.wpilibj.command.Subsystem;

public class IMUSubsystem extends Subsystem {
	
	public ADIS16448_IMU imu;
	double degrees_per_unit = 0.25d;
	double zero_offset_x=0d;
	double zero_offset_y=0d;
	double zero_offset_z=0d;
	
	public IMUSubsystem(){
		imu=new ADIS16448_IMU();
		//imu.calibrate();
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand(new ReportIMUCommand());
	}
	
	public void zero(){
		zero_offset_x=imu.getAngleX();
		zero_offset_y=imu.getAngleY();
		zero_offset_z=imu.getAngleZ();
	}
	
	public void setRatio(double deg2u){
		degrees_per_unit=deg2u;
	}
	
	public double getX(){
		return (imu.getAngleX()-zero_offset_x)*degrees_per_unit;
	}
	
	public double getY(){
		return (imu.getAngleY()-zero_offset_y)*degrees_per_unit;
	}
	
	public double getZ(){
		return (imu.getAngleZ()-zero_offset_z)*degrees_per_unit;
	}

}
