package org.usfirst.frc.team2129.util.speedcontrollers;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShiftingGearbox implements SpeedController {
	public enum ShiftingGearboxGear {
		kHI,
		kLOW
	}
	
	double currentSpeed;
	boolean inverted;
	SpeedController motor1;
	SpeedController motor2;
	SpeedController motor3;
	Solenoid shifter;
	DoubleSolenoid dShifter;
	boolean useDShifter;
	
	String nickname;
	
	public double twoMotorThreshold;
	public double threeMotorThreshold;
	public double hiSpeedThreshold;
	public boolean solenoidInverted;
	public boolean enableShifting;
	
	public boolean logging;
	
	public ShiftingGearbox(SpeedController motor1, SpeedController motor2,
			SpeedController motor3,
			double threshold2, double threshold3,
			double shiftThreshold, boolean solenoidInverted,
			boolean enableShifting,
			String nickname){
		this.motor1=motor1;
		this.motor2=motor2;
		this.motor3=motor3;
		
		this.twoMotorThreshold=threshold2;
		this.threeMotorThreshold=threshold3;
		this.hiSpeedThreshold=shiftThreshold;
		this.solenoidInverted=solenoidInverted;
		this.enableShifting=enableShifting;
		
		this.nickname=nickname;
		//this.set(0);
		
		this.logging=false;
	}
	
	public ShiftingGearbox(SpeedController rightGearboxMotor1, SpeedController rightGearboxMotor2,
			SpeedController rightGearboxMotor3, DoubleSolenoid rightGearboxShifter, double threshold2,
			double threshold3, double shiftThreshold, boolean solenoidInverted2, boolean enableShifting2,
			String nickname2) {
		this(rightGearboxMotor1, rightGearboxMotor2, rightGearboxMotor3, threshold2, threshold3, shiftThreshold,
				solenoidInverted2, enableShifting2, nickname2);
		this.dShifter=rightGearboxShifter;
		this.useDShifter=true;
	}
	
	public ShiftingGearbox(SpeedController rightGearboxMotor1, SpeedController rightGearboxMotor2,
			SpeedController rightGearboxMotor3, Solenoid rightGearboxShifter, double threshold2,
			double threshold3, double shiftThreshold, boolean solenoidInverted2, boolean enableShifting2,
			String nickname2) {
		this(rightGearboxMotor1, rightGearboxMotor2, rightGearboxMotor3, threshold2, threshold3, shiftThreshold,
				solenoidInverted2, enableShifting2, nickname2);
		this.useDShifter=false;
		this.shifter=rightGearboxShifter;
	}

	public void pidWrite(double output) {
		set(output);
	}

	public double get() {
		return currentSpeed;
	}
	
	public void setGearBool(boolean gear){
		if (useDShifter){
			if(this.solenoidInverted?!gear:gear){
				this.dShifter.set(DoubleSolenoid.Value.kForward);
			}else{
				this.dShifter.set(DoubleSolenoid.Value.kReverse);
			}
		}else{
			this.shifter.set(this.solenoidInverted?!gear:gear);
		}
	}
	
	public void setGear(ShiftingGearboxGear gear){
		setGearBool(gear==ShiftingGearboxGear.kHI);
	}
	
	public void putNumber(String name, double number){
		if (logging) SmartDashboard.putNumber(nickname+"::"+name, number);
	}
	
	public void putBoolean(String name, boolean number){
		if (logging) SmartDashboard.putBoolean(nickname+"::"+name, number);
	}

	public void set(double speed) {
		putNumber("raw_req_speed", speed);
		currentSpeed=speed;
		speed=inverted?-speed:speed;
		double mag = Math.abs(speed);
		double sign = (speed>0)?1:-1;
		
		putNumber("inv_req_speed", speed);
		putNumber("mag_req_speed", mag);
		putNumber("req_speed_sign", sign);
		
		if (enableShifting) {
			boolean targetGear = mag>hiSpeedThreshold;
			setGearBool(targetGear);
			putBoolean("target_gear", targetGear);
		}
		
		putBoolean("shifting_enabled", enableShifting);
		
		double motor1Target = mag/twoMotorThreshold;
		motor1Target=Math.min(motor1Target, 1);
		motor1Target*=sign;
		
		motor1.set(motor1Target);
		
		putNumber("motor1_target", motor1Target);
		
		double motor2Target = 0;
		if ((mag-twoMotorThreshold)>0) {
			motor2Target = (mag-twoMotorThreshold)/(threeMotorThreshold-twoMotorThreshold);
		}
		
		motor2Target=Math.min(motor2Target, 1);
		motor2Target*=sign;
		
		motor2.set(motor2Target);
		
		putNumber("motor2_target", motor2Target);
		
		double motor3Target = 0;
		if ((mag-threeMotorThreshold)>0) {
			motor3Target = (mag-threeMotorThreshold)/(1-threeMotorThreshold);
		}
		
		motor3Target=Math.min(motor3Target, 1);
		motor3Target*=sign;
		
		motor3.set(motor3Target);
		
		putNumber("motor3_target", motor3Target);
	}

	public void setInverted(boolean isInverted){
		this.inverted=isInverted;
	}

	public boolean getInverted() {
		return inverted;
	}

	public void disable() {
		set(0);
	}

	public void stopMotor() {
		set(0);
	}

}
