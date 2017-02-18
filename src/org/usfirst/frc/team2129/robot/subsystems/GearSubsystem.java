package org.usfirst.frc.team2129.robot.subsystems;

import org.usfirst.frc.team2129.robot.Robot;
import org.usfirst.frc.team2129.robot.commands.ManualGearCommand;
import org.usfirst.frc.team2129.util.encoderdesc.iencoder.IEncoder;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearSubsystem extends Subsystem{
	//Inputs
	public DigitalInput gearLightSensor;
	IEncoder gearEncoder;
	
	//Outputs
	public Solenoid gearSolenoid;
	public SpeedController gearMotor;
	
	public GearSubsystem() {
		gearMotor = Robot.map.GearMotor.get();
		gearSolenoid=new Solenoid(Robot.map.gearSolenoid);
		gearLightSensor=new DigitalInput(Robot.map.gearLightSensor);
		gearEncoder=Robot.map.gearEncoder.get();
	}
	
	protected void initDefaultCommand() {
		setDefaultCommand(new ManualGearCommand());
	}
	
	public boolean getGearGood(){
		return gearLightSensor.get();
	}
	
	public void zeroAgle(){
		gearEncoder.zero();
	}
	
	public double getAngle(){
		return gearEncoder.getDistance();
	}
	
	public double getRate(){
		return gearEncoder.getRate();
	}
	
	public PIDSource getPIDSource(){
		return gearEncoder;
	}
}
