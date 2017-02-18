package org.usfirst.frc.team2129.robot.commands;

import org.usfirst.frc.team2129.robot.Robot;

import edu.wpi.cscore.VideoException;
import edu.wpi.cscore.VideoProperty;
import edu.wpi.first.wpilibj.Preferences;
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
		SmartDashboard.putData("camchoose__r__", chooser);
	}
	
	boolean set=false;
	public void execute(){
		if(chooser.getSelected()!=selected){
			selected=chooser.getSelected();
			Robot.cameraSubsystem.setCamera(selected);
		}
		
		if(Robot.oi.leftJoystick.getRawButton(11)){
			for(VideoProperty p:Robot.cameraSubsystem.getCurrentCam().enumerateProperties()){
				Preferences.getInstance().putInt("CAM_"+p.getName(), p.get());
			}
		}		
		
		if(Robot.oi.leftJoystick.getRawButton(10)){
			if(!set){
				set=true;
				
				try{
					for(VideoProperty p:Robot.cameraSubsystem.getCurrentCam().enumerateProperties()){
						if(Preferences.getInstance().containsKey(p.getName())){
							p.set(Preferences.getInstance().getInt("CAM_"+p.getName(), p.get()));
						}
					}
				}catch(VideoException vx){wrErr(vx, "prefs");}
				
				try{
					if(Preferences.getInstance().getBoolean("manual_exposure", false)){
						Robot.cameraSubsystem.getCurrentCam().setExposureManual(
								Preferences.getInstance().getInt("exposure", 49)
							);
					}else if(Preferences.getInstance().getBoolean("hold_exposure", false)){
						Robot.cameraSubsystem.getCurrentCam().setExposureHoldCurrent();
					}else{
						Robot.cameraSubsystem.getCurrentCam().setExposureAuto();
					}
				}catch(VideoException vx){wrErr(vx, "exposure");}
				
				try{
					if(Preferences.getInstance().getBoolean("wbalance_manual", false)){
						Robot.cameraSubsystem.getCurrentCam().setWhiteBalanceManual(
							Preferences.getInstance().getInt("wbalance", 49)
						);
					}else if(Preferences.getInstance().getBoolean("hold_wbalance", false)){
						Robot.cameraSubsystem.getCurrentCam().setWhiteBalanceHoldCurrent();
					}else{
						Robot.cameraSubsystem.getCurrentCam().setWhiteBalanceAuto();
					}
				}catch(VideoException vx){wrErr(vx, "wbalance");}
				
				try{
					Robot.cameraSubsystem.getCurrentCam().setBrightness(Preferences.getInstance().getInt("brightness", 49));
				}catch(VideoException vx){wrErr(vx, "brightness");}
			}
		}else{
			set=false;
		}
		
		SmartDashboard.putBoolean("cam_v_set", set);
		
		
	}

	private void wrErr(VideoException vx, String tag) {
		SmartDashboard.putString("vx_err", vx.toString());
		SmartDashboard.putString("vx_err_tag", tag);
		
	}
}
