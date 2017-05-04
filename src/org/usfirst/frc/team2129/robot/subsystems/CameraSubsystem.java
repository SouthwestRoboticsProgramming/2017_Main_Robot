package org.usfirst.frc.team2129.robot.subsystems;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.usfirst.frc.team2129.robot.Robot;
import org.usfirst.frc.team2129.robot.commands.IndexedCameraCommand;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode;

public class CameraSubsystem extends Team2129Subsystem {
	private boolean inited = false;
	private MjpegServer server;
	private Map<String, UsbCamera> cameras = new HashMap<String, UsbCamera>();
	private String curr;
	
	public CameraSubsystem() {
	}

	protected void initDefaultCommand() {
////		setDefaultCommand(new ManualCameraCommand());
//		setDefaultCommand(new IndexedCameraCommand("Front"));
	}

	public void init() {
		if (!inited) {
			inited = true;
			server = new MjpegServer("RoboRIO-2129-FRC", 1180);
			Robot.map.cameras.forEach((name, value) -> initCamera(name, value));
			curr = "Front";
			setCamera(curr);
		}
	}

	private void initCamera(String name, Integer value) {
		try {
			log("registering camera named: " + name + " to " + value.toString());
			UsbCamera cam = new UsbCamera(name, value);
			cameras.put(name, cam);
			VideoMode[] modes = cam.enumerateVideoModes();
			cam.setVideoMode(modes[modes.length - 1]);
			curr = name;
			
		} catch (Exception e) {
			log("Snarfed err" + e.toString() + "creating cam " + name);
		}
	}

	public Set<String> getCameraNames() {
		return Robot.map.cameras.keySet();
	}

	public Integer getCameraNamed(String name) {
		return Robot.map.cameras.get(name);
	}

	public void setCamera(String camera) {
		System.err.println("Setting camera " + camera);
		setSmartDashboard("cam_sel", camera);
		if (cameras.containsKey(camera)) {
			curr = camera;  
			server.setSource(cameras.get(camera));
		}
	}

	public UsbCamera getCurrentCam() {
		if (curr == null)
			curr = "Front";
		return cameras.get(curr);
	}

	public void configCamera() {
		// this.getCurrentCam()
	}
}
