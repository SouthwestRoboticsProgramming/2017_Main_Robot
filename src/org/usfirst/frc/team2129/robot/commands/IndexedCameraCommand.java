package org.usfirst.frc.team2129.robot.commands;

import edu.wpi.cscore.VideoException;

public class IndexedCameraCommand extends Team2129Command {
	private String cameraName;
	boolean set = false;

	public IndexedCameraCommand(String cameraName) {
		requires(getCameraSubsystem());
//		setAll();
		this.cameraName = cameraName;
	}

	protected boolean isFinished() {
		return set;
	}

	public void initialize() {
		set = false;
	}

	

	public void execute() {
		getCameraSubsystem().setCamera(cameraName);
		setExtended();
		set=true;

//		if (getLeftJoystick().getRawButton(11))
//			setPreferences();
//
//		if (getLeftJoystick().getRawButton(10)) {
//			if (!set) {
//				set = true;
//				setExtended();
//			}
//		} else {
//			set = false;
//		}
		setSmartDashboard("cam_v_set", set);
	}
	
	public void setAll(){
		setPreferences();
		setExtended();
	}
	
	private void setExtended(){
		setBrightness();
		setWhiteBalance();
		setExposure();
	}

	private void setPreferences() {
	}

	private void setBrightness() {
		try {
			getCameraSubsystem().getCurrentCam().setBrightness(getPreferences().getInt("brightness", 49));
		} catch (VideoException vx) {
			wrErr(vx, "brightness");
		}
	}

	private void setWhiteBalance() {
		try {
			if (isManualWhiteBalance()) {
				getCameraSubsystem().getCurrentCam().setWhiteBalanceManual(getPreferences().getInt("wbalance", 49));
			} else if (isHoldWhiteBalance()) {
				getCameraSubsystem().getCurrentCam().setWhiteBalanceHoldCurrent();
			} else {
				getCameraSubsystem().getCurrentCam().setWhiteBalanceAuto();
			}
		} catch (VideoException vx) {
			wrErr(vx, "wbalance");
		}
	}

	private void setExposure() {
		try {
			if (isManualExposure()) {
				getCameraSubsystem().getCurrentCam().setExposureManual(getPreferences().getInt("exposure", 49));
			} else if (isHoldExposure()) {
				getCameraSubsystem().getCurrentCam().setExposureHoldCurrent();
			} else {
				getCameraSubsystem().getCurrentCam().setExposureAuto();
			}
		} catch (VideoException vx) {
			wrErr(vx, "exposure");
		}
	}

	private boolean isHoldWhiteBalance() {
		return getPreferences().getBoolean("hold_wbalance", false);
	}

	private boolean isManualWhiteBalance() {
		return getPreferences().getBoolean("wbalance_manual", false);
	}

	private boolean isHoldExposure() {
		return getPreferences().getBoolean("hold_exposure", false);
	}

	private boolean isManualExposure() {
		return getPreferences().getBoolean("manual_exposure", false);
	}

	private void wrErr(VideoException vx, String tag) {
		setSmartDashboard("vx_err", vx.toString());
		setSmartDashboard("vx_err_tag", tag);
	}
}
