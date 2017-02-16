package org.usfirst.frc.team2129.robot.subsystems;

import org.usfirst.frc.team2129.robot.Robot;
import org.usfirst.frc.team2129.robot.commands.ManualGearCommand;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class GearSubsystem extends Subsystem{
	//Inputs
	public DigitalInput gearLightSensor;
	public ADXRS450_Gyro gearGyro;
	
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
}
