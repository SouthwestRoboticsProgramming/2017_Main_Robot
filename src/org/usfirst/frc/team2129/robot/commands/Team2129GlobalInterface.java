package org.usfirst.frc.team2129.robot.commands;

import org.usfirst.frc.team2129.robot.Robot;
import org.usfirst.frc.team2129.robot.subsystems.CameraSubsystem;
import org.usfirst.frc.team2129.robot.subsystems.ClimberSubsystem;
import org.usfirst.frc.team2129.robot.subsystems.DrivetrainSubsystem;
import org.usfirst.frc.team2129.robot.subsystems.FlashyLightsSubsystem;
import org.usfirst.frc.team2129.robot.subsystems.GearSubsystem;
import org.usfirst.frc.team2129.robot.subsystems.IMUSubsystem;
import org.usfirst.frc.team2129.robot.subsystems.PeripheralsManagmentSubsystem;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * encapsulate all of these global calls ...
 */
public interface Team2129GlobalInterface {

	default void setSmartDashboard(String keyName, boolean value) {
		SmartDashboard.putBoolean(keyName, value);
	}

	default void setSmartDashboard(String keyName, String value) {
		SmartDashboard.putString(keyName, value);
	}

	default void setSmartDashboard(String keyName, double value) {
		SmartDashboard.putNumber(keyName, value);
	}

	default void setSmartDashboard(String keyName, SendableChooser<?> value) {
		SmartDashboard.putData(keyName, value);
	}

	default IMUSubsystem getImuSubsystem() {
		return Robot.imuSubsystem;
	}

	default GearSubsystem getGearSubsystem() {
		return Robot.gearSubsystem;
	}

	default DrivetrainSubsystem getDrivetrainSubsystem() {
		return Robot.drivetrainSubsystem;
	}

	default CameraSubsystem getCameraSubsystem() {
		return Robot.cameraSubsystem;
	}

	default PeripheralsManagmentSubsystem getPeripheralsSubsystem() {
		return Robot.peripheralsSubsystem;
	}

	default FlashyLightsSubsystem getLightsSubsystem() {
		return Robot.lightsSubsystem;
	}
	
	default ClimberSubsystem getClimberSubsystem() {
		return Robot.climberSubsystem;
	}

	default Preferences getPreferences() {
		return Preferences.getInstance();
	}

	// TODO: Where does this output go?
	default void log(String aString) {
		System.err.println(aString);
	}
}
