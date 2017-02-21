package org.usfirst.frc.team2129.robot.commands;

import edu.wpi.cscore.VideoException;
import edu.wpi.cscore.VideoProperty;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class ManualCameraCommand extends Team2129Command {
	private SendableChooser<String> chooser;
	private String selected;

	public ManualCameraCommand() {
		requires(getCameraSubsystem());
	}

	protected boolean isFinished() {
		return false;
	}

	public void initialize() {
		chooser = new SendableChooser<String>();
		int idx = 0;
		for (String cam : getCameraSubsystem().getCameraNames()) {
			chooser.addDefault(Integer.toString(idx) + "_" + cam, cam);
			selected = cam;
			idx += 1;
		}
		getCameraSubsystem().setCamera(selected);
		setSmartDashboard("camchoose__r__", chooser);
	}

	boolean set = false;

	public void execute() {
		if (chooser.getSelected() != selected) {
			selected = chooser.getSelected();
			getCameraSubsystem().setCamera(selected);
		}

		if (getLeftJoystick().getRawButton(11))
			setPreferences();

		if (getLeftJoystick().getRawButton(10)) {
			if (!set) {
				set = true;
				setVideoProperties();
				setExposure();
				setWhiteBalance();
				setBrightness();
			}
		} else {
			set = false;
		}
		setSmartDashboard("cam_v_set", set);
	}

	private void setPreferences() {
		for (VideoProperty p : getCameraSubsystem().getCurrentCam().enumerateProperties()) {
			getPreferences().putInt("CAM_" + p.getName(), p.get());
		}
	}

	private void setBrightness() {
		try {
			getCameraSubsystem().getCurrentCam().setBrightness(getPreferences().getInt("brightness", 49));
		} catch (VideoException vx) {
			wrErr(vx, "brightness");
		}
	}

	private void setVideoProperties() {
		try {
			for (VideoProperty p : getCameraSubsystem().getCurrentCam().enumerateProperties()) {
				if (getPreferences().containsKey(p.getName())) {
					p.set(getPreferences().getInt("CAM_" + p.getName(), p.get()));
				}
			}
		} catch (VideoException vx) {
			wrErr(vx, "prefs");
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
