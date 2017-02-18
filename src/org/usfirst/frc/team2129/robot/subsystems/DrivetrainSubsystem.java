package org.usfirst.frc.team2129.robot.subsystems;

import org.usfirst.frc.team2129.robot.Robot;
import org.usfirst.frc.team2129.robot.commands.UserDriveCommand;
import org.usfirst.frc.team2129.util.encoderdesc.iencoder.IEncoder;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DrivetrainSubsystem extends Subsystem {
	public SpeedController leftGearboxMotor1;
	public SpeedController leftGearboxMotor2;
	public SplitSpeedController leftGearbox;
	
	public SpeedController rightGearboxMotor1;
	public SpeedController rightGearboxMotor2;
	public SplitSpeedController rightGearbox;
	
	public Solenoid  shifter;
	
	public RobotDrive      robotDrive;
	

	public IEncoder leftEncoder;
	public IEncoder rightEncoder;

	AnalogInput Ultrasonic1;
	
	//Mark has a plan for these
	DigitalInput LightSensorLeft;
	DigitalInput LightSensorRight;

	protected void initDefaultCommand() {
		setDefaultCommand(new UserDriveCommand());
	}
	
	public DrivetrainSubsystem(){
		leftGearboxMotor1   = Robot.map.RightMotor1.get();
		leftGearboxMotor2   = Robot.map.RightMotor2.get();
		leftGearbox         = new SplitSpeedController(leftGearboxMotor1, leftGearboxMotor2);
		
		rightGearboxMotor1  = Robot.map.LeftMotor1.get();
		rightGearboxMotor2  = Robot.map.LeftMotor2.get();
		rightGearbox        = new SplitSpeedController(rightGearboxMotor1, rightGearboxMotor2);
		
		shifter = new Solenoid(Robot.map.shifter);
		
		robotDrive          = new RobotDrive(leftGearbox, rightGearbox);
		
		leftEncoder = Robot.map.leftEncoder.get();
		rightEncoder = Robot.map.rightEncoder.get();
		//Ultrasonic1 = new AnalogInput(RobotMap.Ultrasonic);//needs port
		//LightSensorLeft = new DigitalInput(RobotMap.DriveLightLeft);
		//LightSensorRight = new DigitalInput(RobotMap.DriveLightRight);
	}
	
	public void tankDrive(double left, double right){
		robotDrive.tankDrive(left, right);
		double dmz=Preferences.getInstance().getDouble("gy_mute_zone", 0.02);
		if(Math.abs(left)<dmz && Math.abs(right)<dmz){
			if(Preferences.getInstance().getBoolean("dynamic_gy_mute", false)){
				Robot.imuSubsystem.imu.freeze();
				SmartDashboard.putBoolean("dynamic_gy_freeze", true);
			}
		}else{
			Robot.imuSubsystem.imu.unfreeze();
			SmartDashboard.putBoolean("dynamic_gy_freeze", false);
		}
	}
	//Mark has a plan for all of this...
	public int getValue() {
		return Ultrasonic1.getValue();
	}
	
	public double getVoltage() {
		return Ultrasonic1.getVoltage();
	}
	
	public boolean getLeft() {
		return LightSensorLeft.get();
	}
	
	public boolean getRight() {
		return LightSensorRight.get();
	}
	
	public void setShift(boolean state){
		shifter.set(state);
	}
}
