
package org.usfirst.frc.team2129.robot;

import org.usfirst.frc.team2129.robot.commands.auto.FullAutoCommand;
import org.usfirst.frc.team2129.robot.map.TestRobotMap;
import org.usfirst.frc.team2129.robot.subsystems.CameraSubsystem;
import org.usfirst.frc.team2129.robot.subsystems.ClimberSubsystem;
import org.usfirst.frc.team2129.robot.subsystems.DrivetrainSubsystem;
import org.usfirst.frc.team2129.robot.subsystems.FlashyLightsSubsystem;
import org.usfirst.frc.team2129.robot.subsystems.GearSubsystem;
import org.usfirst.frc.team2129.robot.subsystems.IMUSubsystem;
import org.usfirst.frc.team2129.robot.subsystems.MechanumDrivetrainSubsystem;
import org.usfirst.frc.team2129.robot.subsystems.PeripheralsManagmentSubsystem;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

@SuppressWarnings("unused") // Yellow triangles bug me, and this is so that you
							// can switch prod<-->test quickfasts
public class Robot extends IterativeRobot {
	
	// TO Use the robot with Mechanum wheels, set this to true, otherwise false
	public static final boolean USING_MECHANUM = false;
	
	// This is the test robot (NOT competition)
	public static final TestRobotMap map = new TestRobotMap();
	
	// TO Fix the imports, CTRL+Shift+O
	
	// Competition robot
//	 public static final ProductionRobotMap map = new ProductionRobotMap();

	public static final DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem();
//	public static final MechanumDrivetrainSubsystem mechanumDrivetrainSubsystem = new MechanumDrivetrainSubsystem();
	public static final PeripheralsManagmentSubsystem peripheralsSubsystem = new PeripheralsManagmentSubsystem();
	public static final GearSubsystem gearSubsystem = new GearSubsystem();
	public static final ClimberSubsystem climberSubsystem = new ClimberSubsystem();
	public static final FlashyLightsSubsystem lightsSubsystem = new FlashyLightsSubsystem();
	public static final CameraSubsystem cameraSubsystem = new CameraSubsystem();
	public static OI oi;
	public static final IMUSubsystem imuSubsystem = new IMUSubsystem();
	private Command autoCommand;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		cameraSubsystem.init();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	public void autonomousInit() {
//		autoCommand = new AutoAlignAndDriveCommand("auto_drive_speed", "auto_drive_time");
		autoCommand = new FullAutoCommand();
		autoCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autoCommand != null) {
			autoCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
