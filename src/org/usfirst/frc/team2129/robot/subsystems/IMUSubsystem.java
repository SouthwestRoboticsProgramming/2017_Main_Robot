package org.usfirst.frc.team2129.robot.subsystems;

import org.usfirst.frc.team2129.robot.commands.ReportCommand;
import org.usfirst.frc.team2129.util.CalibratedADISIMU;

import edu.wpi.first.wpilibj.command.Subsystem;

public class IMUSubsystem extends Subsystem {
	
	public CalibratedADISIMU imu;
	
	public IMUSubsystem(){
		imu=new CalibratedADISIMU();
		zero();
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand(new ReportCommand());
	}
	
	public void zero(){
		imu.zero();
	}
	
	public void calibrate(){
		imu.calibrate();
	}
	
	public double getX(){
		return imu.getX();
	}
	
	public double getY(){
		return imu.getY();
	}
	
	public double getZ(){
		return imu.getZ();
	}
}
