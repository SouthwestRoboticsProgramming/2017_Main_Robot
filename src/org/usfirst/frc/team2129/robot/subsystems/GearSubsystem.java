package org.usfirst.frc.team2129.robot.subsystems;

import org.usfirst.frc.team2129.robot.Robot;
import org.usfirst.frc.team2129.robot.commands.ManualGearCommand;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearSubsystem extends Subsystem{
	//Inputs
	public DigitalInput gearLightSensor;
	public ADXRS450_Gyro gearGyro;
	double gyroZero = 0;
	
	//Outputs
	public Solenoid gearSolenoid;
	public SpeedController gearMotor;
	
	public GearSubsystem() {
		gearMotor = Robot.map.GearMotor.get();
		gearSolenoid=new Solenoid(Robot.map.gearSolenoid);
		gearLightSensor=new DigitalInput(Robot.map.gearLightSensor);
		gearGyro=new ADXRS450_Gyro();
		
	}
	
	protected void initDefaultCommand() {
		setDefaultCommand(new ManualGearCommand());
	}
	
	public boolean getGearGood(){
		return gearLightSensor.get();
	}
	
	public void zeroGyro(){
		gyroZero=gearGyro.getAngle();
	}
	
	public double getGyro(){
		return gearGyro.getAngle()-gyroZero;
	}
	
	public GearSSPIDSource getPIDSource(){
		return new GearSSPIDSource();
	}
	
	public class GearSSPIDSource implements PIDSource{

		@Override
		public PIDSourceType getPIDSourceType() {
			// TODO Auto-generated method stub
			return PIDSourceType.kDisplacement;
		}

		@Override
		public double pidGet() {
			return Robot.gearSubsystem.getGyro();
		}

		@Override
		public void setPIDSourceType(PIDSourceType arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
