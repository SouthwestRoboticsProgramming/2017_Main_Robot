package org.usfirst.frc.team2129.util;

import com.analog.adis16448.frc.ADIS16448_IMU;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class CalibratedADISIMU{
	public enum IMUAxis{
		X,
		Y,
		Z
	}
	
	public class CalibratedADISIMUAxisView implements PIDSource{
		public void setPIDSourceType(PIDSourceType pidSource) {}
		public PIDSourceType getPIDSourceType() {return null;}
		
		IMUAxis axis;
		CalibratedADISIMU imu;
		
		public CalibratedADISIMUAxisView(CalibratedADISIMU i, IMUAxis a){
			axis=a;
			imu=i;
		}

		public double pidGet() {
			switch(axis){
				case X: return imu.getX();
				case Y: return imu.getY();
				case Z: return imu.getZ();
			}
			return 0d;
		}
	}
	
	public ADIS16448_IMU imu;
	public double degreesPerUnit = 0.25d; //This is a OK approximation
	double off_x;
	double off_y;
	double off_z;
	
	public CalibratedADISIMUAxisView XSource;
	public CalibratedADISIMUAxisView YSource;
	public CalibratedADISIMUAxisView ZSource;
	
	public CalibratedADISIMU(){
		imu = new ADIS16448_IMU();
		zero();
		XSource = new CalibratedADISIMUAxisView(this, IMUAxis.X);
		YSource = new CalibratedADISIMUAxisView(this, IMUAxis.Y);
		ZSource = new CalibratedADISIMUAxisView(this, IMUAxis.Z);
	}
	
	public void zero(){
		off_x=imu.getAngleX();
		off_y=imu.getAngleY();
		off_z=imu.getAngleZ();
	}
	
	private double _correct(double angle){
		while(angle<-360) angle+=360;
		if(angle<0) angle=360+angle;
		while(angle>360) angle-=360;
		return angle;
	}
	
	public double getX(){
		return _correct((imu.getAngleX()-off_x)*degreesPerUnit);
	}
	
	public double getY(){
		return _correct((imu.getAngleY()-off_y)*degreesPerUnit);
	}
	
	public double getZ(){
		return _correct((imu.getAngleZ()-off_z)*degreesPerUnit);
	}
	
	public void calibrate(){
		imu.calibrate();
	}
}
