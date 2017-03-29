package org.usfirst.frc.team2129.robot.commands.auto;

import org.usfirst.frc.team2129.robot.commands.Team2129Command;
import edu.wpi.first.wpilibj.Timer;

public class DriveForward extends Team2129Command {
	
	private Timer timer;
	private double targetDistance;
	boolean isDone;
	String speed;
	String time;
	
	double getSpeed(){
		return getPreferences().getDouble(speed, 0);
	}
	
	double getTime(){
		return getPreferences().getDouble(time, 0);
	}
	
	public DriveForward(String speedPreferenceArg, String timePreferenceArg) {
		isDone = false;
		targetDistance = 5;
		timer = new Timer();
		
	}
	
	public void initialize() {
		timer.reset();
		timer.start();
	}
	
	
	public void execute() {
		getDrivetrainSubsystem().tankDrive(getSpeed(), getSpeed());
//		if (getDrivetrainSubsystem().getRightIEncoder().getDistance() >= targetDistance) {
//			isDone = true;
//		}
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return timer.get()>getTime();
	}
}
