package org.usfirst.frc.team2129.robot.commands;

import org.usfirst.frc.team2129.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ManualGearCommand extends Command {
	public ManualGearCommand(){
		requires(Robot.gearSubsystem);
	}
	
	public void execute(){
		Robot.gearSubsystem.gearSolenoid.set(Robot.oi.thirdJoystick.getRawButton(3));
		SmartDashboard.putBoolean("j3b3", Robot.oi.thirdJoystick.getRawButton(3));
		SmartDashboard.putBoolean("gear_real", Robot.gearSubsystem.gearSolenoid.get());
		Robot.gearSubsystem.gearMotor.set(Robot.oi.thirdJoystick.getX());
	}
	
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
