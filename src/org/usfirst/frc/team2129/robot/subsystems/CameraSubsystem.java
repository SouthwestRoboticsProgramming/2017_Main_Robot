package org.usfirst.frc.team2129.robot.subsystems;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.usfirst.frc.team2129.robot.Robot;
import org.usfirst.frc.team2129.robot.commands.ManualCameraCommand;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CameraSubsystem extends Subsystem {
	public CameraSubsystem() {}

	protected void initDefaultCommand() {
		setDefaultCommand(new ManualCameraCommand());
	}
	
	boolean inited=false;
	MjpegServer server;
	Map<String, UsbCamera> cameras = new HashMap<String, UsbCamera>();
	
	
	public void init(){
		if(!inited){
			inited=true;
			server=new MjpegServer("RoboRIO-2129-FRC", 1180);
			for(String key:Robot.map.cameras.keySet()){
				System.err.println("registering key: "+key+" to "+Robot.map.cameras.get(key).toString());
				cameras.put(key, new UsbCamera(key, Robot.map.cameras.get(key)));
			}
			server.setSource(cameras.values().stream().findFirst().get());
		}
	}
	
	public Set<String> getCameras(){
		return Robot.map.cameras.keySet();
	}
	
	public void setCamera(String camera){
		if(cameras.containsKey(camera)){
			server.setSource(cameras.get(camera));
		}
	}
}
