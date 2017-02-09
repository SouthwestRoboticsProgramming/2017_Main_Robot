package org.usfirst.frc.team2129.robot.commands;

import org.usfirst.frc.team2129.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ManualCameraCommand extends Command {
	
	SendableChooser<String> chooser;
	String selected;
	
	public ManualCameraCommand(){
		requires(Robot.cameraSubsystem);
	}

	protected boolean isFinished() {
		return false;
	}
	
	public void initialize(){
		chooser=new SendableChooser<String>();
		int idx=0;
		for(String cam:Robot.cameraSubsystem.getCameras()){
			chooser.addDefault(Integer.toString(idx)+"_"+cam, cam);
			selected=cam;
			idx+=1;
		}
		Robot.cameraSubsystem.setCamera(selected);
		SmartDashboard.putData("camchoose__r", chooser);
	}
	
	public void execute(){
		if(chooser.getSelected()!=selected){
			selected=chooser.getSelected();
			Robot.cameraSubsystem.setCamera(selected);
		}
	}
}
